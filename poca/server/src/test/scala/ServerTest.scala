import akka.http.scaladsl.testkit.{ScalatestRouteTest, WSProbe}
import org.scalatest.{FunSuite, Matchers}
import server.GameService

class ServerTest extends FunSuite with Matchers with ScalatestRouteTest {

  test("should create GameService") {
    new GameService()
  }

  test("should be able to connect to the GameService websocket") {
    assertWebsocket("John") { wsClient =>
      isWebSocketUpgrade shouldEqual true
    }
  }

  test("should register player") {
    assertWebsocket("John"){ wsClient =>
      wsClient.expectMessage("[{\"name\":\"John\",\"position\":{\"x\":300.0,\"y\":300.0}}]")
    }
  }

  test("should register multiple players") {
    val gameService = new GameService()
    val johnClient = WSProbe()
    val andrewClient = WSProbe()

    WS(s"/?playerName=John", johnClient.flow) ~> gameService.websocketRoute ~> check {
      johnClient.expectMessage("[{\"name\":\"John\",\"position\":{\"x\":300.0,\"y\":300.0}}]")
    }
    WS(s"/?playerName=Andrew", andrewClient.flow) ~> gameService.websocketRoute ~> check {
      andrewClient.expectMessage("[{\"name\":\"John\",\"position\":{\"x\":300.0,\"y\":300.0}},{\"name\":\"Andrew\",\"position\":{\"x\":300.0,\"y\":300.0}}]")
    }
  }


  def assertWebsocket(playerName: String)(assertions:(WSProbe) => Unit) : Unit = {
    val gameService = new GameService()
    val wsClient = WSProbe()
    WS(s"/?playerName=$playerName", wsClient.flow) ~> gameService.websocketRoute ~> check(assertions(wsClient))
  }
}


import akka.actor.ActorSystem
import akka.http.scaladsl.model.ws.{BinaryMessage, Message, TextMessage}
import akka.http.scaladsl.server.Directives
import akka.http.scaladsl.testkit.{ScalatestRouteTest, WSProbe}
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Sink, Source}
import org.scalatest.{FunSuite, Matchers}

class ServerTest extends FunSuite with Matchers with ScalatestRouteTest{
	test("should create a new Gameserver") {

		new GameService()
	}
  test("should connect to the GameService websocket") {
    // tests:
    // create a testing probe representing the client-side
    val gameService = new GameService()
    val wsClient = WSProbe()

    // WS creates a WebSocket request for testing
    WS("/greeter", wsClient.flow) ~> gameService.websocketRoute ~>
      check {
        // check response for WS Upgrade headers
        isWebSocketUpgrade shouldEqual true
      }
  }
  test("should respond to a message"){
    // tests:
    // create a testing probe representing the client-side
    val gameService = new GameService()
    val wsClient = WSProbe()

    // WS creates a WebSocket request for testing
    WS("/greeter", wsClient.flow) ~> gameService.websocketRoute ~>
      check {
        // check response for WS Upgrade headers
        wsClient.sendMessage("Hello PLayer!")
        wsClient.expectMessage("Hello PLayer!")
      }

  }
}

class GameService() extends Directives{

  implicit var actorsystem = ActorSystem()
  implicit var actorMaterializer = ActorMaterializer()
def greeter: Flow[Message, Message, Any] =
  Flow[Message].collect {
    case TextMessage.Strict(txt) =>
      TextMessage(txt)

  }
val websocketRoute =
  get {
    handleWebSocketMessages(greeter)
  }


}

object GameService {

}

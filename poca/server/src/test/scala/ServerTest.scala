
import akka.actor.ActorSystem
import akka.http.scaladsl.model.ws.{Message, TextMessage}
import akka.http.scaladsl.server.{Directives}
import akka.http.scaladsl.testkit.{ScalatestRouteTest, WSProbe}
import akka.stream.{ActorMaterializer, FlowShape}
import akka.stream.scaladsl.{Flow, GraphDSL, Merge}
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
    WS("/", wsClient.flow) ~> gameService.websocketRoute ~>
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
    WS("/", wsClient.flow) ~> gameService.websocketRoute ~>
      check {
        // check response for WS Upgrade headers
        wsClient.expectMessage("Welcome to Pokemon-go player!")
        wsClient.sendMessage("Hello PLayer!")
        wsClient.expectMessage("Hello PLayer!")
      }

  }
  test("should subscribe a player"){
    // tests:
    // create a testing probe representing the client-side
    val gameService = new GameService()
    val wsClient = WSProbe()

    // WS creates a WebSocket request for testing
    WS("/", wsClient.flow) ~> gameService.websocketRoute ~>
      check {
        wsClient.expectMessage("Welcome to Pokemon-go player!")
      }

  }
}

class GameService() extends Directives{

  implicit var actorsystem = ActorSystem()
  implicit var actorMaterializer = ActorMaterializer()


def greeter: Flow[Message, Message, Any] = Flow.fromGraph(GraphDSL.create(){ implicit builder =>
  import GraphDSL.Implicits._

  val materialization = builder.materializedValue.map(m => TextMessage("Welcome to Pokemon-go player!"))
  val messagePassingFlow = builder.add(Flow[Message].map(m => m))
  val merge1 = builder.add(Merge[Message](2))
  materialization ~> merge1.in(0)
  merge1 ~> messagePassingFlow
  FlowShape(merge1.in(1),messagePassingFlow.out)

})
  val websocketRoute = get {
    handleWebSocketMessages(greeter)
  }

}


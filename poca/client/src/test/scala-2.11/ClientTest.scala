import akka.actor.ActorSystem
import akka.stream.scaladsl.Source
import akka.stream.testkit.scaladsl.TestSink
import akka.stream.{ActorMaterializer, OverflowStrategy}
import client.Client
import client.{Player1, Position}
import org.scalatest.{FunSuite, Matchers}

class ClientTest extends FunSuite with Matchers {

  test("should be able to login player") {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()

    val testSink = TestSink.probe[List[Player1]]
    val outgoing = Source.empty[String]
    val client = new Client("jacob")

    val (_,testProbe) = client.run(outgoing,testSink)

    testProbe.request(1)
    testProbe.expectNext(List(Player1("jacob",Position(300,300))))
  }

  test("should be able to move player") {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    val client = new Client("Jacob")
    val input = Source.actorRef[String](5,OverflowStrategy.dropNew)
    val output = TestSink.probe[List[Player1]]

    val ((inputMat,result),outputMat) = client.run(input,output)

    inputMat ! "up"

    outputMat.request(2)
    outputMat.expectNext(List(Player1("Jacob",Position(300,300))))
    outputMat.expectNext(List(Player1("Jacob",Position(300,301))))
  }

}



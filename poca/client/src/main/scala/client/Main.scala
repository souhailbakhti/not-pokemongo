package client
import scala.swing._
import scala.swing.event._

/**
  * Main of the POCA client.
  */
object Main extends SimpleSwingApplication {
  val exitButton = new Button ("Exit")

  listenTo(exitButton)
  reactions += {
    case ButtonClicked(b) =>
      quit()
  }

  override def top = new MainFrame {
    title = "Not Pokémon Go"
    minimumSize = new Dimension(640, 480)
    contents = new FlowPanel (
      exitButton
    )
    centerOnScreen
  }

  override def main(args: Array[String]): Unit = {
    println("POCA client started!")

    startup(args)
  }
}

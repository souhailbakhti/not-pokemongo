import java.awt.Font
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.BasicGame
import org.newdawn.slick.TrueTypeFont
import org.newdawn.slick.Color
import org.newdawn.slick.Input
import org.newdawn.slick.AppGameContainer;

class MainMenuExample extends BasicGame("Slick2D Main Menu Example") {
  private val NOCHOICES = 5

  private val START = 0

  private val SAVE = 1

  private val LOAD = 2

  private val OPTIONS = 3

  private val QUIT = 4

  private var playersChoice: Int = 0

  private var playersOptions: Array[String] = new Array[String](NOCHOICES)

  private var exit: Boolean = false

  private var font: Font = _

  private var playersOptionsTTF: TrueTypeFont = _

  private var foo: TrueTypeFont = _

  private var notChosen: Color = new Color(153, 204, 255)

  override def init(gc: GameContainer) {
    font = new Font("Verdana", Font.BOLD, 40)
    playersOptionsTTF = new TrueTypeFont(font, true)
    font = new Font("Verdana", Font.PLAIN, 20)
    foo = new TrueTypeFont(font, true)
    playersOptions(0) = "Start"
    playersOptions(1) = "Save"
    playersOptions(2) = "Load"
    playersOptions(3) = "Options"
    playersOptions(4) = "Quit"
  }

  override def update(gc: GameContainer, delta: Int) {
    val input = gc.getInput
    if (input.isKeyPressed(Input.KEY_DOWN)) {
      if (playersChoice == (NOCHOICES - 1)) {
        playersChoice = 0
      } else {
        playersChoice += 1
      }
    }
    if (input.isKeyPressed(Input.KEY_UP)) {
      if (playersChoice == 0) {
        playersChoice = NOCHOICES - 1
      } else {
        playersChoice -= 1
      }
    }
    if (input.isKeyPressed(Input.KEY_ENTER)) playersChoice match {
      case QUIT  => exit = true
      case START => new StateGame()
    }
  }

  override def render(gc: GameContainer, g: Graphics) {
    renderPlayersOptions()
    if (exit) {
      gc.exit()
    }
  }

  private def renderPlayersOptions() {
    for (i <- 0 until NOCHOICES) {
      if (playersChoice == i) {
        playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions(i))
      } else {
        playersOptionsTTF.drawString(100, i * 50 + 200, playersOptions(i), notChosen)
      }
    }
  }
}

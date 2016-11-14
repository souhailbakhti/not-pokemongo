import java.awt.Font
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.TrueTypeFont
import org.newdawn.slick.Color
import org.lwjgl.input.Mouse

class MainScreenGameState extends BasicGameState {
  var playNow: Image = _
  var exitGame: Image = _

  private val ID = 1

  override def init(container: GameContainer, game: StateBasedGame) {
    playNow = new Image("src/play.png")
    exitGame = new Image("src/exit.png")

  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    g.drawString("Welcome to NotPokemonGo", 300, 100)
    playNow.draw(250, 250)
    exitGame.draw(100, 300)
  }
  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {
    var posX: Int = Mouse.getX()
    var posY: Int = Mouse.getY()
    println("x est"+posX)
    println("y est"+posY)
    if ((posX > 300 && posX < 500) && (posY > 350 && posY < 420)) {
      if (Mouse.isButtonDown(0)) {
        game.enterState(2)
      }
    }
    if ((posX > 300 && posX < 500) && (posY >150  && posY < 200)) {
      if (Mouse.isButtonDown(0)) {
        System.exit(0)
      }
    }

  }

  override def keyReleased(key: Int, c: Char) {
  }

  override def getID(): Int = ID
}

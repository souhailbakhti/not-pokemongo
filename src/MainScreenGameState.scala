import java.awt.Font
import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics


class MainScreenGameState extends BasicGameState {
   private val ID = 1



 // private var background: Image = _

  private var game: StateBasedGame = _

  override def init(container: GameContainer, game: StateBasedGame) {
    this.game = game
   // this.background = new Image("background/forest.png")
  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
  //  background.draw(0, 0, container.getWidth, container.getHeight)
    g.drawString("Appuyer sur une touche", 300, 300)
  }

  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {
  }

  override def keyReleased(key: Int, c: Char) {
    game.enterState(new MapGameState().getID())
  }

  override def getID(): Int = ID
}

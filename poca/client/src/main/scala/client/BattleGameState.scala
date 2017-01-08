import org.newdawn.slick.state.BasicGameState
import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import org.newdawn.slick.command.InputProvider
import org.newdawn.slick.command.KeyControl

class BattleGameState extends BasicGameState {
  private val ID = 3

  object BattleCommand extends Enumeration {
    type BattleCommand = Value
    val Attack, Exit = Value
  }
  private var game: StateBasedGame = _
  private var pokemon: Pokemon = _
  private var background: Image = _
  private var player: BattlePlayer = _
  private var provider: InputProvider = _
 // private var BattleCommandTest:BattleCommand = _

  import BattleCommand._
  override def init(container: GameContainer, game: StateBasedGame) {
    this.game = game;
    this.background = new Image("src/battle.png")
    this.pokemon = new Pokemon
    pokemon.init
    this.player = new BattlePlayer

  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    background.draw(0, 0, container.getWidth(), container.getHeight())
    pokemon.render
  }

  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {

  }

  override def keyPressed(key: Int, c: Char) {
    game.enterState(2)
  }

  override def getID(): Int = ID
}
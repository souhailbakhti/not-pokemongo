import org.newdawn.slick.state.StateBasedGame
import org.newdawn.slick.GameContainer

class StateGame extends StateBasedGame("NotPokemonGo") {

  override def initStatesList(container: GameContainer) {
    addState(new MainScreenGameState())
    addState(new MapGameState())
  }
}
package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

class StateGame extends StateBasedGame("NotPokemonGo") {

  override def initStatesList(container: GameContainer) {
    addState(new MainScreenGameState())
    addState(new MapGameState())
  }
}
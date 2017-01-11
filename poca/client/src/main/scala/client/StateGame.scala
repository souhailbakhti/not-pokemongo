package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

object StateGame extends StateBasedGame("NotPokemonGo") {

  object States extends Enumeration {
    type StateType = Value
    val MainMenu, LoginScreen, RegisterScreen, MapView = Value
  }

  override def initStatesList(container: GameContainer) {
    addState(new MainScreenGameState())
    addState(new MapGameState())
    addState(new LoginScreenState())
    addState(new RegisterScreenState())
  }
}
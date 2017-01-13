package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

import scala.io.StdIn
object StateGame extends StateBasedGame("NotPokemonGo") {

  object States extends Enumeration {
    type StateType = Value
    val MainMenu, LoginScreen, RegisterScreen, MapView = Value
  }

  override def initStatesList(container: GameContainer) {
    val name = StdIn.readLine("What's your name?")
    addState(new MainScreenGameState())
    addState(new MapGameState(name))
    addState(new LoginScreenState())
    addState(new RegisterScreenState())
  }
}

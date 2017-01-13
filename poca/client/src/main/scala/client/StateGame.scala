package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

import scala.io.StdIn
object StateGame extends StateBasedGame("NotPokemonGo") {

  private var playerName: String = _

  def setPlayerName(name: String): Unit = {
    playerName = name
  }

  def getPlayerName: String = playerName

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

package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.StateBasedGame

import scala.io.StdIn

class StateGame extends StateBasedGame("NotPokemonGo") {

  override def initStatesList(container: GameContainer) {
    addState(new MainScreenGameState())
    val name = StdIn.readLine("What's your name?")
    addState(new MapGameState(name))

  }
}
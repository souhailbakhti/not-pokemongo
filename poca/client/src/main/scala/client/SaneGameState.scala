package client

import org.newdawn.slick.GameContainer
import org.newdawn.slick.state.{BasicGameState, StateBasedGame}
import org.newdawn.slick.util.InputAdapter

/**
  * Convenience class to workaround some Slick2D input management bugs WRT state changes
  */
abstract class SaneGameState(state: StateGame.States.StateType) extends BasicGameState {
  /**
    This is the list of all UI elements that accept input
   */
  private var inputSinks: List[InputAdapter] = List()

  /**
    * This function MUST be called for every UI element that accepts input
    */
  protected def registerInputSinks(newInputSinks: InputAdapter*): Unit = {
    newInputSinks.foreach((inputSink) => {
      inputSink.setAcceptingInput(false)
      inputSinks = inputSink :: inputSinks
    })
  }

  override def getID: Int = state.id

  override def update(container: GameContainer, game: StateBasedGame, delta: Int): Unit = {
  }

  override def enter(container: GameContainer, game: StateBasedGame): Unit = {
    super.enter(container, game)
    inputSinks.foreach((inputSink) => {
      inputSink.setAcceptingInput(true)
    })
  }

  override def leave(container: GameContainer, game: StateBasedGame): Unit = {
    super.leave(container, game)
    inputSinks.foreach((inputSink) => {
      inputSink.setAcceptingInput(false)
    })
  }
}

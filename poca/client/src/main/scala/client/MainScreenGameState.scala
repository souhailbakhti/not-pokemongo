package client

import org.newdawn.slick.gui.{AbstractComponent, ComponentListener, TextField}
import org.newdawn.slick._
import org.newdawn.slick.state.{BasicGameState, StateBasedGame}
import StateGame.States

class MainScreenGameState extends SaneGameState(States.MainMenu) {
  var login: TextButton = _
  var register: TextButton = _
  var quit: TextButton = _

  override def init(container: GameContainer, game: StateBasedGame) {
    login = new TextButton(container, "Login", 400, 300, new ComponentListener() {
      def componentActivated(source: AbstractComponent): Unit = {
        game.enterState(States.LoginScreen.id)
      }
    })
    register = new TextButton(container, "Register", 400, 350, new ComponentListener() {
      def componentActivated(source: AbstractComponent): Unit = {
        game.enterState(States.RegisterScreen.id)
      }
    })
    quit = new TextButton(container, "Quit", 400, 400, new ComponentListener() {
      def componentActivated(source: AbstractComponent): Unit = {
        System.exit(0)
      }
    })
    registerInputSinks(login, register, quit)
  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    g.drawString("Welcome to NotPokemonGo", 300, 100)
    login.render(container, g)
    register.render(container, g)
    quit.render(container, g)
  }
}

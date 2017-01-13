package client

import client.StateGame.States
import org.newdawn.slick.gui.{AbstractComponent, ComponentListener, TextField}
import org.newdawn.slick.{AngelCodeFont, Font, GameContainer, Graphics}
import org.newdawn.slick.state.StateBasedGame

class RegisterScreenState extends SaneGameState(States.RegisterScreen) {
  var back: TextButton = _
  var register: TextButton = _
  var nameField: TextField = _
  var passwordField: TextField = _
  val passwordFont: Font = new AngelCodeFont(Resources.resourcePath("password.fnt"),
                                              Resources.resourcePath("arialb.png"))

  override def init(container: GameContainer, game: StateBasedGame): Unit = {
    back = new TextButton(container, "Back", 350, 400, new ComponentListener() {
      def componentActivated(source: AbstractComponent): Unit = {
        game.enterState(States.MainMenu.id)
      }
    })
    register = new TextButton(container, "Register", 450, 400, new ComponentListener() {
      def componentActivated(source: AbstractComponent): Unit = {
        if (nameField.getText.isEmpty || passwordField.getText.isEmpty)
          return
        StateGame.setPlayerName(nameField.getText)
        game.enterState(States.MapView.id)
      }
    })
    nameField = new TextField(container, container.getDefaultFont, 400, 300, 150, 25)
    passwordField = new TextField(container, passwordFont, 400, 350, 150, 25)
    registerInputSinks(back, register, nameField, passwordField)
  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics): Unit = {
    GraphicsUtils.drawCenteredText(g, "Create an account", 400, 200)
    g.drawString("Username: ", 275, 300)
    nameField.render(container, g)
    g.drawString("Password: ", 275, 350)
    passwordField.render(container, g)
    back.render(container, g)
    register.render(container, g)
  }
}

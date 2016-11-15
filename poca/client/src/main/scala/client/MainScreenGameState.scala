package client

import org.lwjgl.input.Mouse
import org.newdawn.slick.{GameContainer, Graphics, Image}
import org.newdawn.slick.state.{BasicGameState, StateBasedGame}

class MainScreenGameState extends BasicGameState {
  var playNow: Image = _
  var exitGame: Image = _

  private val ID = 1

  override def init(container: GameContainer, game: StateBasedGame) {
    playNow = new Image(Resources.resourcePath("play.png"))
    exitGame = new Image(Resources.resourcePath("exit.png"))

  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    g.drawString("Welcome to NotPokemonGo", 300, 100)
    playNow.draw(250, 250)
    exitGame.draw(100, 300)
  }
  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {
    var posX: Int = Mouse.getX()
    var posY: Int = Mouse.getY()

    if ((posX > 300 && posX < 500) && (posY > 350 && posY < 420)) {
      if (Mouse.isButtonDown(0)) {
        game.enterState(2)
      }
    }
    if ((posX > 300 && posX < 500) && (posY >150  && posY < 200)) {
      if (Mouse.isButtonDown(0)) {
        System.exit(0)
      }
    }

  }

  override def keyReleased(key: Int, c: Char) {
  }

  override def getID(): Int = ID
}

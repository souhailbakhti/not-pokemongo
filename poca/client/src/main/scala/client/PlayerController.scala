package client

import akka.actor.ActorRef
import org.newdawn.slick.{Input, KeyListener}

class PlayerController(private var player: Player,keyboardEventsReceiver: ActorRef) extends KeyListener {
  
  override def setInput(input: Input) {
  }

  override def isAcceptingInput(): Boolean = true

  override def inputEnded() {
  }

  override def inputStarted() {
  }


  override def keyPressed(key: Int, c: Char): Unit = key match {
    case Input.KEY_UP =>
      this.player.setDirection(0)
      this.player.setMoving(true)
      keyboardEventsReceiver ! "up"

    case Input.KEY_LEFT =>
      this.player.setDirection(1)
      this.player.setMoving(true)
      keyboardEventsReceiver ! "left"

    case Input.KEY_DOWN =>
      this.player.setDirection(2)
      this.player.setMoving(true)
      keyboardEventsReceiver ! "down"

    case Input.KEY_RIGHT =>
      this.player.setDirection(3)
      this.player.setMoving(true)
      keyboardEventsReceiver ! "right"
    case _ =>
  }

  override def keyReleased(key: Int, c: Char) {
    this.player.setMoving(false)
  }
}
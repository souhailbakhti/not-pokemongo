package server.actor

import akka.actor.{Actor, ActorRef}
import server.PositionCalculator
import scala.io.StdIn

trait GameEvent
case class PlayerJoined(player: Player,actorRef: ActorRef) extends GameEvent
case class PlayerLeft(playerName: String) extends GameEvent
case class PlayerMoveRequest(playerName: String,direction: String) extends GameEvent
case class PlayersChanged(players: Iterable[Player]) extends GameEvent

case class Player(name: String,position: Position)
case class PlayerWithActor(player: Player,actor: ActorRef)
case class Position(x:Float,y:Float) {
  def + (other: Position) : Position = {
    Position(x+other.x,y+other.y)

  }
}

class GameAreaActor extends Actor {

  val players = collection.mutable.LinkedHashMap[String,PlayerWithActor]()
  def takenPositions = players.values.map(_.player.position).toList

  override def receive: Receive = {
    case PlayerJoined(player,actor) => {
      val newPlayer = Player(player.name,Position(300,300))
      players += (player.name -> PlayerWithActor(newPlayer,actor))
      println(players.values.toList.toString())
      notifyPlayersChanged()
    }
    case PlayerLeft(playerName) => {
      players -= playerName
      notifyPlayersChanged()
    }
    case PlayerMoveRequest(playerName,direction) => {
      println(playerName+":"+direction)
      val args = direction.split(" ")
      if (args.length == 3) {
        val newPos = Position(args(1).toFloat, args(2).toFloat)
        val oldPlayerWithActor = players(playerName)
        val actor = oldPlayerWithActor.actor
        players(playerName) = PlayerWithActor(Player(playerName, newPos), actor)
        notifyPlayersChanged()
      }
    }
  }

  def notifyPlayersChanged(): Unit = {
    players.values.foreach(_.actor ! PlayersChanged(players.values.map(_.player)))
  }
}

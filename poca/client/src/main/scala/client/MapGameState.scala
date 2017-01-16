package client

import akka.actor.{ActorRef, ActorSystem}
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import org.newdawn.slick.{Color, GameContainer, Graphics, Music}
import org.newdawn.slick.state.{BasicGameState, StateBasedGame}
import StateGame.States

import concurrent.ExecutionContext.Implicits.global

class MapGameState() extends BasicGameState {
  private val ID = States.MapView.id
  private var loggedIn = false

  private var container: GameContainer = _
  private var serverActorRef: ActorRef = _
  private var posUpdateTimer: Int = 0

  private var map: Map = _

  private var music: Music = _

  private var player: Player = _

  private var playerM: List[Player] = _
  private var xCamera: Float =_

  private var yCamera: Float =_

  def notifyOberver() {}

  override def init(container: GameContainer, game: StateBasedGame) {
    this.container = container
    var sceneFactory: SceneFactory = new ScenePrincipaleFactory()
    this.map = sceneFactory.createMap
    music = sceneFactory.createMusic
    playerM=Nil
    this.map.init()

    music.loop()
  }

  override def enter(container: GameContainer, game: StateBasedGame): Unit = {
    super.enter(container, game)
    if (!loggedIn) {
      implicit val system = ActorSystem()
      implicit val materializer = ActorMaterializer()
      val name = StateGame.getPlayerName
      player = new Player(map, name, Position(200,200))
      this.player.init()
      playerM=player::playerM

      val input = Source.actorRef[String](5,OverflowStrategy.dropNew)
      val client = new Client(name)
      val output = this.sink
      val ((inputMat, result), outputMat) = client.run(input, output)
      serverActorRef = inputMat
      player.sendPos(serverActorRef)

      xCamera = player.getX()
      yCamera = player.getY()

      var controller: PlayerController = new PlayerController(this.player, inputMat)
      container.getInput.addKeyListener(controller)

      loggedIn = true
    }
  }

  def sink = {println("Received sink"); Sink.foreach[List[Player1]] { playerPositions =>
    playerPositions.map(player => {
      var x: Boolean = false
      println(player.position.y)
      println(player.position.x)
      for (playerx <- playerM) {
        if (playerx.playerName.equals(player.name)) {
          println("found user " + player.name)
          x = true
          if (!this.player.getName().equals(player.name)) {
            playerx.setX(player.position.x)
            playerx.setY(player.position.y)
          }
        }
      }

        if (!x) {
          println("new player" + "\t" + player.name)
          var player1 = new Player(map, player.name, player.position)
          print(player1.getX() + ",")
          println(player1.getY())
          player1.init()
          playerM = player1 :: playerM
          for (playerx <- playerM) {
            println(this.player.getName() + "\t" + playerx.playerName)
          }
          print(player1.getX() + ",")
          println(player1.getY())
        }
    })
  }}

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    g.translate(container.getWidth / 2 - xCamera.toInt, container.getHeight / 2 - yCamera.toInt)
    this.map.renderBackground()
    //this.player.render(g)
    for (player1 <- playerM) player1.render(g)
    this.map.renderForeground()
  }

  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {
    updateTrigger()
    //this.player.update(delta)
    posUpdateTimer+=1
    if (posUpdateTimer % 6 == 0) {
      posUpdateTimer = 0
      if (player.isMoving())
        player.sendPos(serverActorRef)
    }
    for (player1 <- playerM) player1.update(delta)
    updateCamera(container)
  }

  private def updateTrigger() {
    this.player.setOnStair(false)

    for (objectID <- 0 until this.map.getObjectCount) {
      if (isInTrigger(objectID)) {
        if ("teleport" == this.map.getObjectType(objectID)) {
          teleport(objectID)
        } else if ("stair" == this.map.getObjectType(objectID)) {
          this.player.setOnStair(true)
        } else if ("change-map" == this.map.getObjectType(objectID)) {
          changeMap(objectID)
        }
      }
    }
  }

  private def isInTrigger(id: Int): Boolean = {
    this.player.getX > this.map.getObjectX(id) &&
      this.player.getX <
      this.map.getObjectX(id) + this.map.getObjectWidth(id) &&
      this.player.getY > this.map.getObjectY(id) &&
      this.player.getY <
      this.map.getObjectY(id) + this.map.getObjectHeight(id)
  }

  private def teleport(objectID: Int) {
    this.player.setX(java.lang.Float.parseFloat(this.map.getObjectProperty(objectID, "dest-x", java.lang.Float.toString(this.player.getX))))
    this.player.setY(java.lang.Float.parseFloat(this.map.getObjectProperty(objectID, "dest-y", java.lang.Float.toString(this.player.getY))))
  }

  private def changeMap(objectID: Int) {
    teleport(objectID)
    val newMap = this.map.getObjectProperty(objectID, "dest-map", "undefined")
    if ("undefined" != newMap) {
      this.map.changeMap(newMap)
    }
  }

  private def updateCamera(container: GameContainer) {
    val w = container.getWidth / 4
    if (this.player.getX > this.xCamera + w) {
      this.xCamera = this.player.getX - w
    } else if (this.player.getX < this.xCamera - w) {
      this.xCamera = this.player.getX + w
    }
    val h = container.getHeight / 4
    if (this.player.getY > this.yCamera + h) {
      this.yCamera = this.player.getY - h
    } else if (this.player.getY < this.yCamera - h) {
      this.yCamera = this.player.getY + h
    }
  }
  override def getID(): Int = ID
    Monster.create(1)
    Monster.create(2)
    Monster.create(3)
}

  

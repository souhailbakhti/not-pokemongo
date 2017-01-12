package client

import akka.actor.ActorSystem
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.{ActorMaterializer, OverflowStrategy}
import org.newdawn.slick.{Color, GameContainer, Graphics, Music}
import org.newdawn.slick.state.{BasicGameState, StateBasedGame}

import concurrent.ExecutionContext.Implicits.global

class MapGameState(name:String) extends BasicGameState {
  private val ID = 2

  private var container: GameContainer = _

  private var map: Map = _

  private var music: Music = _

  private var player: Player = _


  private var xCamera: Float =_

  private var yCamera: Float =_

  def notifyOberver() {}

  override def init(container: GameContainer, game: StateBasedGame) {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    val client = new Client(name)
    this.container = container
    var sceneFactory: SceneFactory = new ScenePrincipaleFactory()
    this.map = sceneFactory.createMap
    music = sceneFactory.createMusic
    player= new Player(map)

    this.map.init()
    val input = Source.actorRef[String](5,OverflowStrategy.dropNew)
    this.player.init()

    val output=this.player.sink
    val ((inputMat,result),outputMat) = client.run(input,output)
    xCamera=player.getX()
    yCamera= player.getY()
    var controller: PlayerController = new PlayerController(this.player,inputMat);
    container.getInput().addKeyListener(controller);
    music.loop()
  }

  override def render(container: GameContainer, game: StateBasedGame, g: Graphics) {
    g.translate(container.getWidth / 2 - xCamera.toInt, container.getHeight / 2 - yCamera.toInt)
    this.map.renderBackground()
    this.player.render(g)
    this.map.renderForeground()
  }

  override def update(container: GameContainer, game: StateBasedGame, delta: Int) {
    updateTrigger()
    this.player.update(delta)
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
}

  

import org.newdawn.slick.AppGameContainer
import org.newdawn.slick.BasicGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.Music
import org.newdawn.slick.SlickException
import java.util

class ObjectsGame extends BasicGame("") with Observer{


  private var container: GameContainer = _

  private var map: Map = new Map1()

  private var player: Player = new Player(map)

  private var xCamera: Float = player.getX

  private var yCamera: Float = player.getY
  
  def notifyOberver(){}

  override def init(container: GameContainer) {
    this.container = container
    this.map.init()
    this.player.init()
    val background = new Music("src/Music/lost-in-the-meadows.ogg")
    background.loop()
  }

  override def render(container: GameContainer, g: Graphics) {
    g.translate(container.getWidth / 2 - xCamera.toInt, container.getHeight / 2 - yCamera.toInt)
    this.map.renderBackground()
    this.player.render(g)
    this.map.renderForeground()
  }

  override def update(container: GameContainer, delta: Int) {
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
      this.map.changeMap("src/" + newMap)
    }
  }

  //mettre à jour le camera
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

  
  // control clavier
  override def keyReleased(key: Int, c: Char) {
    this.player.setMoving(false)
    if (Input.KEY_ESCAPE == key) {
      this.container.exit()
    }
  }

  override def keyPressed(key: Int, c: Char): Unit = key match {
    case Input.KEY_UP =>
      this.player.setDirection(0)
      this.player.setMoving(true)

    case Input.KEY_LEFT =>
      this.player.setDirection(1)
      this.player.setMoving(true)

    case Input.KEY_DOWN =>
      this.player.setDirection(2)
      this.player.setMoving(true)

    case Input.KEY_RIGHT =>
      this.player.setDirection(3)
      this.player.setMoving(true)

  }
}

import org.newdawn.slick.Animation
import org.newdawn.slick.Color
import org.newdawn.slick.Graphics
import org.newdawn.slick.SlickException
import org.newdawn.slick.SpriteSheet

class Player(private var map: Map) extends Character{

  private var x: Float = 300
  private var y: Float = 300
  private var direction: Int = 2
  private var onStair: Boolean = false
  private var moving: Boolean = false
  var currentState:State =_
  var vie:Int=0
  
  
  private var animations: Array[Animation] = new Array[Animation](8)

  
  def init() {
    val spriteSheet = new SpriteSheet("src/character.png", 64, 64)
    this.animations(0) = loadAnimation(spriteSheet, 0, 1, 0)
    this.animations(1) = loadAnimation(spriteSheet, 0, 1, 1)
    this.animations(2) = loadAnimation(spriteSheet, 0, 1, 2)
    this.animations(3) = loadAnimation(spriteSheet, 0, 1, 3)
    this.animations(4) = loadAnimation(spriteSheet, 1, 9, 0)
    this.animations(5) = loadAnimation(spriteSheet, 1, 9, 1)
    this.animations(6) = loadAnimation(spriteSheet, 1, 9, 2)
    this.animations(7) = loadAnimation(spriteSheet, 1, 9, 3)
  }

  //set spriteSheet 
  private def loadAnimation(spriteSheet: SpriteSheet,
                            startX: Int,
                            endX: Int,
                            y: Int): Animation = {
    val animation = new Animation()
    for (x <- startX until endX) {
      animation.addFrame(spriteSheet.getSprite(x, y), 100)
    }
    animation
  }

  //display window
  def render(g: Graphics) {
    g.setColor(new Color(0, 0, 0, .5f))
    g.fillOval(x.toInt - 16, y.toInt - 8, 32, 16)
    g.drawAnimation(animations(direction + (if (moving) 4 else 0)), x.toInt - 32, y.toInt - 60)
  }

  // update
  def update(delta: Int) {
    if (this.moving) {
      val futurX = getFuturX(delta)
      val futurY = getFuturY(delta)
      val collision = this.map.isCollision(futurX, futurY)
      if (collision) {
        this.moving = false
      } else {
        this.x = futurX
        this.y = futurY
      }
    }
  }

  private def getFuturX(delta: Int): Float = {
    var futurX:Float = x
    direction match {
      case 1 => futurX = this.x - .1f * delta
      case 3 => futurX = this.x + .1f * delta
      case _ =>
    }
    return futurX
  }

  private def getFuturY(delta: Int): Float = {
    var futurY:Float = this.y
    this.direction match {
      case 0 => futurY = this.y - .1f * delta
      case 2 => futurY = this.y + .1f * delta
      case 1 => if (this.onStair) {
        futurY = this.y + .1f * delta
      }
      case 3 => if (this.onStair) {
        futurY = this.y - .1f * delta
      }
    }
    return futurY
  }
  
  
//getters setters
  def getX(): Float = x

  def setX(x: Float) {
    this.x = x
  }

  def getY(): Float = y

  def setY(y: Float) {
    this.y = y
  }

  def getDirection(): Int = direction

  def setDirection(direction: Int) {
    this.direction = direction
  }

  def isMoving(): Boolean = moving

  def setMoving(moving: Boolean) {
    this.moving = moving
  }

  def isOnStair(): Boolean = onStair

  def setOnStair(onStair: Boolean) {
    this.onStair = onStair
  }
  
  def setState(s:State)={this.currentState=s}

}

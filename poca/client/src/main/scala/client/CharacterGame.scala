package client

import org.newdawn.slick._
import org.newdawn.slick.tiled.TiledMap;

class CharacterGame extends BasicGame("client.CharacterGame") {

  private var container: GameContainer = _

  private var map: TiledMap = _

  private var x: Float = 300

  private var y: Float = 300

  private var direction: Int = 2

  private var moving: Boolean = false

  private var animations: Array[Animation] = new Array[Animation](8)

  override def init(container: GameContainer) {
    this.container = container
    this.map = new TiledMap(Resources.resourcePath("exemple.tmx"))
    val spriteSheet = new SpriteSheet(Resources.resourcePath("character.png"), 64, 64)
    this.animations(0) = loadAnimation(spriteSheet, 0, 1, 0)
    this.animations(1) = loadAnimation(spriteSheet, 0, 1, 1)
    this.animations(2) = loadAnimation(spriteSheet, 0, 1, 2)
    this.animations(3) = loadAnimation(spriteSheet, 0, 1, 3)
    this.animations(4) = loadAnimation(spriteSheet, 1, 9, 0)
    this.animations(5) = loadAnimation(spriteSheet, 1, 9, 1)
    this.animations(6) = loadAnimation(spriteSheet, 1, 9, 2)
    this.animations(7) = loadAnimation(spriteSheet, 1, 9, 3)
  }

  private def loadAnimation(spriteSheet: SpriteSheet, startX: Int, endX: Int, y: Int): Animation = {
    val animation = new Animation()
    for (x <- startX until endX) {
      animation.addFrame(spriteSheet.getSprite(x, y), 100)
    }
    animation
  }

  override def render(container: GameContainer, g: Graphics) {
    this.map.render(0, 0)
    g.setColor(new Color(0, 0, 0, .5f))
    g.fillOval(x - 16, y - 8, 32, 16)
    g.drawAnimation(animations(direction + (if (moving) 4 else 0)), x - 32, y - 60)
  }

  override def update(container: GameContainer, delta: Int) {
    if (this.moving) this.direction match {
      case 0 => this.y -= .1f * delta
      case 1 => this.x -= .1f * delta
      case 2 => this.y += .1f * delta
      case 3 => this.x += .1f * delta
    }
  }

  override def keyReleased(key: Int, c: Char) {
    this.moving = false
    if (Input.KEY_ESCAPE == key) {
      this.container.exit()
    }
  }

  override def keyPressed(key: Int, c: Char):Unit = key match {
    case Input.KEY_UP => 
      this.direction = 0
      this.moving = true

    case Input.KEY_LEFT => 
      this.direction = 1
      this.moving = true

    case Input.KEY_DOWN => 
      this.direction = 2
      this.moving = true

    case Input.KEY_RIGHT => 
      this.direction = 3
      this.moving = true

    }
  }


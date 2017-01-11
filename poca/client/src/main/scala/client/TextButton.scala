package client

import org.newdawn.slick.{AngelCodeFont, Font, Graphics}
import org.newdawn.slick.gui.{AbstractComponent, ComponentListener, GUIContext}

/**
 *  Draw a clickable text element centered at the given coordinates
 */
class TextButton(container: GUIContext, font: Font, msg: String, centerx: Int, centery: Int,
                 listener: ComponentListener) extends AbstractComponent(container) {

  def this(container: GUIContext, msg: String, centerx: Int, centery: Int, listener: ComponentListener) =
    this(container, container.getDefaultFont, msg, centerx, centery, listener)

  protected var posx: Int = 0
  protected var posy: Int = 0
  protected val width: Int = font.getWidth(msg)
  protected val height: Int = font.getLineHeight

  setLocation(centerx, centery)
  addListener(listener)

  override def setLocation(x: Int, y: Int) {
    posx = x - width/2
    posy = y - height/2
  }

  override def getX: Int = posx

  override def getY: Int = posy

  override def getHeight: Int = height

  override def getWidth: Int = width

  override def render(container: GUIContext, g: Graphics): Unit = {
    g.setFont(font)
    g.drawString(msg, posx, posy)
    g.resetFont()
  }

  override def mouseClicked(button: Int, x: Int, y: Int, clickCount: Int): Unit = {
    if (x >= posx && x < posx + width
        && y >= posy && y < posy + height)
      notifyListeners()
  }
}

package client

import org.newdawn.slick.Graphics

// Convenience functions for graphics
object GraphicsUtils {
  def drawCenteredText(g: Graphics, msg: String, x: Int, y: Int): Unit = {
    val font = g.getFont
    g.drawString(msg, x - font.getWidth(msg)/2, y - font.getLineHeight/2)
  }
}

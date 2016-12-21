package client
import java.awt.{Image, Toolkit}

object ImageUtils {
  def createImage(fileName: String): Image = {
    Toolkit.getDefaultToolkit.createImage(fileName)
  }
}
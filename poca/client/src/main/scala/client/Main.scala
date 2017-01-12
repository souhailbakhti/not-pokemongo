package client

import java.io.File
import java.lang.reflect.Field
import java.net.URLDecoder
import java.nio.file.{Files, Paths}



import scala.io.StdIn
import org.newdawn.slick.AppGameContainer

object Main {
  /**
    * Set the library path to find our native libraries
    */
  def setLibraryPath(): Unit = {
    val oldpath = System.getProperty("java.library.path")
    System.setProperty("java.library.path", oldpath + ":" + Resources.resourcePath("native"))
    val fieldSysPath: Field = classOf[ClassLoader].getDeclaredField("sys_paths")
    fieldSysPath.setAccessible(true)
    fieldSysPath.set(null, null)
    println(System.getProperty("java.library.path"))
  }

  def main(args: Array[String]) {

    setLibraryPath()
    new AppGameContainer(new StateGame(), 800, 700, false).start()

  }
}

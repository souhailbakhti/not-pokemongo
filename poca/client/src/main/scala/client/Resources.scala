package client

import java.io.File
import java.net.URLDecoder
import java.nio.file.{Files, Paths}

object Resources {
  private var resourcePathCache: String = _

  /**
    * Returns the path to a file or folder in the game's data folders
    */
  def resourcePath(file: String): String = {
    if (resourcePathCache == null)
      findResourcePath()

    val resPath = resourcePathCache + file
    if (!Files.exists(Paths.get(resPath)))
      throw new RuntimeException("Couldn't find game resource: "+resPath)
    return resPath
  }

  private def findResourcePath(): Unit = {
    val dataDir = "data" + File.separatorChar

    if (Files.isDirectory(Paths.get(dataDir))) {
      resourcePathCache = "." + File.separatorChar + dataDir
      return
    }

    var jarpath = Main.getClass.getProtectionDomain.getCodeSource.getLocation.getPath
    jarpath = URLDecoder.decode(jarpath, "UTF-8")
    jarpath = new File(jarpath).getParent
    val jarDataPath = jarpath + File.separatorChar + dataDir
    if (Files.isDirectory(Paths.get(jarDataPath))) {
      resourcePathCache = jarDataPath
      return
    }

    System.err.println("Couldn't find the game's \"data\" folder!")
    throw new RuntimeException
  }
}

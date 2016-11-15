package client

import org.newdawn.slick.Music

class ScenePrincipaleFactory extends SceneFactory {
  def createMap:Map=new Map1
  def createMusic:Music=new Music(Resources.resourcePath("music/lost-in-the-meadows.ogg"))
}
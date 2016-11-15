package client

import org.newdawn.slick.Music

abstract class SceneFactory {
  def createMap:Map
  def createMusic:Music
}
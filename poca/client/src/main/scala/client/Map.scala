package client

import org.newdawn.slick.tiled.TiledMap;

abstract class Map {
  var tiledMap: TiledMap =_
  def init() 
  def renderBackground()
  def renderForeground()
  def isCollision(x: Float, y: Float): Boolean
  def changeMap(file: String):Unit
  def getObjectCount():Int
  def getObjectType(objectID: Int): String
  def getObjectX(objectID: Int): Float
  def getObjectY(objectID: Int): Float
  def getObjectWidth(objectID: Int): Float
  def getObjectHeight(objectID: Int): Float
  def getObjectProperty(objectID: Int, propertyName: String, `def`: String): String
}
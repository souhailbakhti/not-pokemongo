package client


import org.newdawn.slick.tiled.TiledMap


class Map1 extends Map() {

 // private var tiledMap: TiledMap = _
  
  override def init() {
    this.tiledMap = new TiledMap(Resources.resourcePath("exemple-change-map.tmx"))
  }

  override def renderBackground() {
    this.tiledMap.render(0, 0, 0)
    this.tiledMap.render(0, 0, 1)
    this.tiledMap.render(0, 0, 2)
  }

  override def renderForeground() {
    this.tiledMap.render(0, 0, 3)
    this.tiledMap.render(0, 0, 4)
  }

  override def isCollision(x: Float, y: Float): Boolean = {
    val tileW = this.tiledMap.getTileWidth
    val tileH = this.tiledMap.getTileHeight
    val logicLayer = this.tiledMap.getLayerIndex("logic")
    val tile = this.tiledMap.getTileImage(x.toInt / tileW, y.toInt / tileH, logicLayer)
    var collision = tile != null
    if (collision) {
      val color = tile.getColor(x.toInt % tileW, y.toInt % tileH)
      collision = color.getAlpha > 0
    }
    collision
  }

  def changeMap(file: String) {
    this.tiledMap = new TiledMap(file)
  }

  def getObjectCount(): Int = {this.tiledMap.getObjectCount(0)}

  def getObjectType(objectID: Int): String = {
    this.tiledMap.getObjectType(0, objectID)
  }

  def getObjectX(objectID: Int): Float = this.tiledMap.getObjectX(0, objectID)

  def getObjectY(objectID: Int): Float = this.tiledMap.getObjectY(0, objectID)

  def getObjectWidth(objectID: Int): Float = {
    this.tiledMap.getObjectWidth(0, objectID)
  }

  def getObjectHeight(objectID: Int): Float = {
    this.tiledMap.getObjectHeight(0, objectID)
  }

  def getObjectProperty(objectID: Int, propertyName: String, `def`: String): String = {
    this.tiledMap.getObjectProperty(0, objectID, propertyName, `def`)
  }
}


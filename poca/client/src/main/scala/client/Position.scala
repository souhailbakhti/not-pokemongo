package client
/**

  * Created by root on 11/01/17.
  */
case class Position(x:Float,y:Float) {
  def +(other: Position): Position = {
    Position(x + other.x, y + other.y)

  }
}

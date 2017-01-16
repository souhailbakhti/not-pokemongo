package client

import org.newdawn.slick.Image

class Pokemon extends Character {
  var vie: Int = 0
  val level: Int = 1
  var flag:Boolean=false
  var photo: Image = _

  def init() {
    this.photo = new Image(Resources.resourcePath("src/pikachu.png"))
  }

  def render() = {
    photo.drawCentered(250, 250)
    if(flag) {
      photo.destroy
    }
  }
  
  def update(delta:Int)={
    //this.animation.update(delta)
  }
}
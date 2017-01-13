package client

import org.newdawn.slick.Image
//import shionn.slick.animation

class Pokemon extends Character {
  var vie: Int = 0
  val level: Int = 1
  var flag:Boolean=false
  var photo: Image = _
 // var animation: shionn.slick.animation.PathAnimation = _

  def init() {
    this.photo = new Image("src/pikachu.png")
   // this.animation = new shionn.slick.animation.PathAnimation(new shionn.slick.animation.BezierPath(0, 0, 400, 1, -50, 20, 0, 0), 2000);
  }

  def render() = {
    photo.drawCentered(250, 250)
    if(flag==true){
      photo.destroy
    }
  }
  
  def update(delta:Int)={
    //this.animation.update(delta)
  }
}
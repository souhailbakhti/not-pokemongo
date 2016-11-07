import org.newdawn.slick.Music

class ScenePrincipaleFactory extends SceneFactory {
  def createMap:Map=new Map1
  def createMusic:Music=new Music("src/Music/lost-in-the-meadows.ogg")
}
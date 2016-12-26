package client

import java.awt.Image
import java.io.{BufferedReader, FileNotFoundException, FileReader, IOException}
import scala.io.Source

object Monster {
  private var normal = 0
  private var fire = 1
  private var water = 2
  private var electric = 3
  private var ice = 4
  private var dragon = 5
 
  val statusUnaffected = 0
  val statusParalyzed = 1
  val statusBurned = 2
  val statusPoisoned = 3
  val statusAsleep = 4
  val statusFrozen = 5
  
  
  lazy val statusPAR = ImageUtils.createImage("Graphic/Images/StatusPAR.png")
  lazy val statusBRN = ImageUtils.createImage("Graphic/Images/StatusBRN.png")
  lazy val statusPSN = ImageUtils.createImage("Graphic/Images/StatusPSN.png")
  lazy val statusSLP = ImageUtils.createImage("Graphic/Images/StatusSLP.png")
  lazy val statusFRZ = ImageUtils.createImage("Graphic/Images/StatusFRZ.png")

  def create(n: Int): Monster = {
    def battler(num: String) = s"Graphics/Battlers/${num}.png"
    def icon(num: String) = s"Graphics/Icons/icon${num}.png"
    val monster = new Monster
    def setStats(monster: Monster,
                 name: String,
                 level: Int,
                 number: Int,
                 backSprite: Image,
                 frontSprite: Image,
                 backSpriteShiny: Image,
                 frontSpriteShiny: Image,
                 partyIcon: Image,
                 pv: Int,
                 curPv: Int,
                 exp: Int,
                 curExp: Int,
                 attack: Int,
                 curAttack: Int,
                 defense: Int,
                 curDef: Int,
                 spAttack: Int,
                 spDef: Int,
                 move1: String,
                 move2: String,
                 move3: String,
                 move4: String): Unit = {
      monster.name = name
      monster.level = level
      monster.number = number
      monster.pv = pv
      monster.curPv = curPv
      monster.exp = exp
      monster.curExp = curExp
      monster.attack = attack
      monster.curAttack = curAttack
      monster.defense = defense
      monster.curDef = curDef
      monster.spAttack = spAttack
      monster.spDef = spDef
      monster.move1 = move1
      monster.move2 = move2
      monster.move3 = move3
      monster.move4 = move4

    }
    n match {
      // create pikachu
      case 1 => {
        setStats(monster, "Pickachu", 5, n,
          ImageUtils.createImage(battler("01b")),
          ImageUtils.createImage(battler("01")),
          ImageUtils.createImage(battler("01sb")),
          ImageUtils.createImage(battler("01s")),
          ImageUtils.createImage(icon("01")),
          23, 23, 200, 0, 12, 12, 10, 10, 40, 10,
          "Thundershock", "Quick Attack", "Tail Whip", "Thunderwave")
      }
      // Charmander
      case 2 => {
        setStats(monster, "Charmander", 5, n,
          ImageUtils.createImage(battler("02b")),
          ImageUtils.createImage(battler("04")),
          ImageUtils.createImage(battler("02sb")),
          ImageUtils.createImage(battler("02s")),
          ImageUtils.createImage(icon("02")),
          20, 20, 200, 0, 14, 14, 15, 15, 40, 10,
          "Ember", "Scratch", "Tail Whip", "Fire Spin")
      }
      // Swinub
      case 3 => {
        setStats(monster, "Swinub", 3, n,
          ImageUtils.createImage(battler("03b")),
          ImageUtils.createImage(battler("03")),
          ImageUtils.createImage(battler("03sb")),
          ImageUtils.createImage(battler("03s")),
          ImageUtils.createImage(icon("03")),
          15, 15, 200, 0, 15, 15, 20, 20, 40, 10,
          "Icy Wind", "Scratch", "Dig", "Growl")
       
      }
      // bulbaseur
      case 4 => {
        setStats(monster, "Bulbaseur", 5, n,
          ImageUtils.createImage(battler("04b")),
          ImageUtils.createImage(battler("04")),
          ImageUtils.createImage(battler("04sb")),
          ImageUtils.createImage(battler("04s")),
          ImageUtils.createImage(icon("04")),
          20, 15, 200, 0, 18, 20, 20, 20, 40, 10,
          "Icy Wind", "Ember", "Tail Whip", "Growl")
       
      }
    }
    monster
  }
}

class Monster {
  var name: String = ""
  var level: Int = 0
  var number: Int = 0
  var pv: Int = 0
  var attack: Int = 0
  var defense: Int = 0
  var spAttack: Int = 0
  var spDef: Int = 0
  var spd: Int = 0

  var basePv: Int = 0
  var baseAttack: Int = 0
  var baseDef: Int = 0
  var baseSpAttack: Int = 0
  var baseSpDef: Int = 0
  var baseSpd: Int = 0

  var curPv: Int = 0
  var curAttack: Int = 0
  var curDef: Int = 0
  var curSpAttack: Int = 0
  var curSpDef: Int = 0
  var curSpd: Int = 0

  var evPv: Int = 0
  var evAttack: Int = 0
  var evDef: Int = 0
  var evSpAttack: Int = 0
  var evSpDef: Int = 0
  var evSpd: Int = 0

  var exp: Double = 0
  var curExp: Double = 0
  
  var move: Attack = null
  var move1 = ""
  var move2 = ""
  var move3 = ""
  var move4 = ""
  var attackDamage: Int = 0
  
  var statusEffect = 0

  private var weak = new Array[Boolean](16)
  private var strong = new Array[Boolean](16)
  private var shiny = false
  
  def statusImage(): Image = {
    statusEffect match {
      case Monster.statusParalyzed => Monster.statusPAR
      case Monster.statusBurned => Monster.statusBRN
      case Monster.statusPoisoned => Monster.statusPSN
      case Monster.statusAsleep => Monster.statusSLP
      case Monster.statusFrozen => Monster.statusFRZ
    }
    null
  }
  def unaffected = statusEffect == Monster.statusUnaffected

  def affected = !unaffected

  def paralyzed = statusEffect == Monster.statusParalyzed

  def burned = statusEffect == Monster.statusBurned

  def poisoned = statusEffect == Monster.statusPoisoned

  def asleep = statusEffect == Monster.statusAsleep

  def frozen = statusEffect == Monster.statusFrozen
  
  def losePv(i: Int): Unit = {
    curPv -= i
  }
  
  def healPokemon(): Unit = {
    curPv = pv
    curAttack = attack
    curDef = defense
    curSpAttack = spAttack
    curSpDef = spDef
    curSpd = spd
    statusEffect = 0
    println("Player's Pokemon have been healed back to full.")
  }

  def levelUp(): Unit = {
    level += 1
    curExp = 0
    exp = math.pow(level, 3)
  }
  
  def stabilizeStatus(): Unit = {
    statusEffect match {
      case 4 => println("has woken up.")
      case 5 => println("has broken free from the ice.")
    }
    statusEffect = 0
  }

  def reiterateStatus(): Unit = {
    statusEffect match {
      case 4 => println("is still asleep.")
      case 5 => println("name is frozen solid.")
    }
  
}
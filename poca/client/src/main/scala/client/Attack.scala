package client

class Attack(move: String) {
  
  var pp: Int = 10;
  var CurrentPP: Int= pp;
  
  def printAttackNoEffect() = println("No Effect bro");
  
  def damage(enemy: Monster): Int = move match {
    case "tail whip" => {
      if(enemy.curAttack <= 0) printAttackNoEffect()
      else {
        enemy.curAttack -=1;
        println(" attack has been lowred -1 to the enemy");
      }
      0
    }
    case "Growl" => {
      if (enemy.curAttack <= 0) printAttackNoEffect()
      else {
        enemy.curAttack -= 1
        println("attack has been lowred -1 to the enemy")
      }
      0
    }
    case "Toxic" => {
      if (enemy.statusEffect == 3) printAttackNoEffect()
      else {
        enemy.statusEffect = 3
        println("ennemy has been poisoned")
      }
      0
      }
    
   case "Sleep Powder" => {
      if (enemy.statusEffect == 4) printAttackNoEffect()
      else {
        enemy.statusEffect = 4
        println("enemy has fallen asleep")
      }
      0
    } 
   case "Fire Spin" => {
      if (enemy.statusEffect == 2) 4
      else {
        enemy.statusEffect = 2
        println("enemy has been burned")
        3
      }
  }
    case "Peck" => 4
    case "Pursuit" => 4
    case "Tackle" => 5
    case "Quick Attack" => 6
    case "Dig" => 7
    case "Ember" => 5
    case "Scratch" => 7
    case "Icy Wind" => 6
    case "Thundershock" => 5
    case "Water Gun" => 7
  }
}
  
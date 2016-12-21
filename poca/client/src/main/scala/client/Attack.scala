package client

class Attack(move: String) {
  
  var pp: Int = 10;
  var CurrentPP: Int= pp;
  
  def printAttackNoEffect() = println("No Effect bro");
  
  def damage(enemy: Monster): Int = move match {
    case "tail whip" => {
      if(enemy.currAttack <= 0) printAttackNoEffect()
      else {
        enemy.currAttack -=1;
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
  }
}
  
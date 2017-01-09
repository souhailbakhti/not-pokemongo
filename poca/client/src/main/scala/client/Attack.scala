package client

class Attack(move: String) {
  
  var pp: Int = 10;
  var CurrentPP: Int= pp;
  
  def printAttackNoEffect() = println("Pas deffet bro");
  
  def damage(enemy: Monster): Int = move match {
    case "Escalade" => {
      if(enemy.curAttack <= 0) printAttackNoEffect()
      else {
        enemy.curAttack -=1;
        println(" attaque a causer -1 a lennemi");
      }
      0
    }
    case "Picancon" => {
      if (enemy.curAttack <= 0) printAttackNoEffect()
      else {
        enemy.curAttack -= 1
        println("attaque a causer -1 a lennemi")
      }
      0
    }
    case "Gaz Toxic" => {
      if (enemy.statusEffect == 3) printAttackNoEffect()
      else {
        enemy.statusEffect = 3
        println("Un nuage de gaz toxique est projeté au visage de l’ennemi. Peut l’empoisonner.")
      }
      0
      }
    
   case "Lovely kiss" => {
      if (enemy.statusEffect == 4) printAttackNoEffect()
      else {
        enemy.statusEffect = 4
        println("fait un bisou à l’ennemi en prenant une mine effrayante. Endort l’ennemi.")
      }
      0
    } 
   case "Triplattaque" => {
      if (enemy.statusEffect == 2) 4
      else {
        enemy.statusEffect = 2
        println("envoie trois boules d’énergie simultanément. Peut aussi paralyser, brûler ou geler l’ennemi.")
        3
      }
  }
    case "Retour" => 4
    case "Attraction" => 4
    case "Fléau" => 5
    case "Lire-Esprit" => 6
    case "Clonage" => 7
    case "Bluff" => 5
    case "Mania" => 7
    case "Berceuse" => 6
    case "Coupe-Vent" => 5
    case "Griffe" => 7
  }
}
  
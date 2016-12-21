package client

class Item {
  
  sealed trait Item
  
  case class ItemCanUse(name: String,
                        descriptionItem: String,
                        effectItem: Int,
                        itemType: Int,
                        usableBattle: Boolean) extends Item
                  
   
  object Item {
    val Potion = ItemCanUse("Potion", "Heal a pokemon for 15PV.", 1, 1,true)
    val PokeBall = ItemCanUse("PokeBall", "Throw it to capture a pokemon", 2, 2,true)
    val PotionPlayer = ItemCanUse("PotionTwo", "Heal a trainer for 20 PV", 1, 1, true)
    val Escpae = ItemCanUse("Escape", "Escpaing from a battle.", 3, 3,false)

  }
}
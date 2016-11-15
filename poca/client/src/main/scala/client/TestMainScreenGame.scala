package client

import org.scalatest.{FlatSpec, Matchers}

class TestMainScreenGame extends FlatSpec
    with Matchers {
  "The MainScreen" should "have ID is 1" in {
    var result = new MainScreenGameState
    result.getID() should be(1)
  }
}
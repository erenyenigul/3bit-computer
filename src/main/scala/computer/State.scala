package computer

import scala.collection.mutable.ListBuffer

/**
 * Holds execution information for `Computer`. Its contents are mutable.
 * @param x initial value for register x
 * @param y initial value for register y
 * @param z initial value for register z
 */
class State (var x: Int, var y: Int, var z: Int) {
  var ip: Int = 0
  var out: ListBuffer[Int] = ListBuffer[Int]()
  var completed: Boolean = false

  // for debugging
  override def toString: String = s"State(ip=$ip, x=$x, y=$y, z=$z, out=${out.toString()})"
}

object State {

  /**
   * Creates a new State with its registers set to 0.
   * @return
   */
  def initial = State(0, 0, 0)
}
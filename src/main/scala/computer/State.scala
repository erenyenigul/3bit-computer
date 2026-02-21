package computer

import scala.collection.mutable.ListBuffer

class State (var x: Int, var y: Int, var z: Int) {
  var ip: Int = 0
  var out: ListBuffer[Int] = ListBuffer[Int]()
  var completed: Boolean = false

  // for debugging
  override def toString: String = s"State(ip=$ip, x=$x, y=$y, z=$z, out=${out.toString()})"
}

object State {
  def initial = State(0, 0, 0)
}
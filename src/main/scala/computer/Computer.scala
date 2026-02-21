package computer

import computer.Precision.{ComboOperand, LiteralOperand}
import computer.*
import lang.Instruction.*
import lang.{Instruction, Program}

class Computer (private val program: Program, private val state: State = State.initial) {

  private def fetch(): Instruction = {
    val next = program.instructions(state.ip)
    state.ip += 1

    next
  }

  private def comboToValue(rand: ComboOperand): Int = rand match {
    case ComboOperand(4) => state.x
    case ComboOperand(5) => state.y
    case ComboOperand(6) => state.z
    // 0,1,2,3
    case ComboOperand(other) => other
  }

  @scala.annotation.tailrec
  private def loop(): String = {
    fetch() match {
      case Xdv(rand) =>
        state.x = state.x >> comboToValue(rand)

      case Yxl(rand) =>
        val LiteralOperand(l) = rand
        state.y = state.y ^ l;

      case Yst(rand) =>
        val c = comboToValue(rand)
        state.y = c & 7;

      case Jnz(rand) =>
        if (state.x != 0) {
          val LiteralOperand(l) = rand
          state.ip = l >> 1 // divide by 2 because we parsed the instructions and their arguments.
        }
      case Yxz =>
        state.y = state.y ^ state.z

      case Out(rand) =>
        val mod = comboToValue(rand) & 7
        state.out += mod

      case Ydv(rand) =>
        state.y = state.x >> comboToValue(rand)

      case Zdv(rand) =>
        state.z = state.x >> comboToValue(rand)

      case End => return state.out.mkString(",")
    }

    loop()
  }

  def run(): String = {
    if (state.completed) state.out.mkString(",")
    else loop()
  }
}

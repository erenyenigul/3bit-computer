package computer

import computer.Operands.{ComboOperand, LiteralOperand}
import computer.*
import lang.Instruction.*
import lang.{Instruction, Program}
import math.min


/**
 * Executes a given program with an initial state. `Program` and  `State` is provided in the constructor, then `run()` method should be called.
 * @param program to execute
 * @param state to initialize the computer
 */
class Computer (private val program: Program, private val state: State = State.initial) {

  /**
   * Instruction fetching logic to be used in every computation step. Increments instruction pointer with every call.
   * @return Instruction
   */
  private def fetch(): Instruction = {
    // We don't do out-of-bounds checks here. The jumps are guaranteed to be within bounds.
    // Checking with every fetch would be costly.

    val next = program.instructions(state.ip)
    state.ip += 1

    next
  }

  /**
   * Returns a register value or the operand itself depending on the given combo operand.
   * @param rand Combo Operand
   * @return Int
   */
  private def comboToValue(rand: ComboOperand): Int = rand match {
    case ComboOperand(4) => state.x
    case ComboOperand(5) => state.y
    case ComboOperand(6) => state.z
    // 0,1,2,3. Remark: ComboOperands are opaque type integers.
    case ComboOperand(other) => other
  }

  /**
    Tail recursive main execution loop.
    Fetches an instruction, does pattern matching, calls itself at the end.
    Executes instructions.
   */
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

          state.ip = min(l >> 1, program.instructions.size-1)  // divide by 2 because we parsed the instructions and their arguments.
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

  /**
   * Executes the program with the given initial state. If called more than once, a cached value is returned.
   * @return Output of the program
   */
  def run(): String = {
    if (state.completed) state.out.mkString(",")
    else loop()
  }
}

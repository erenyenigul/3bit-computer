package lang

import computer.Operands.LiteralOperand
import lang.Instruction.*

enum ValidatorResult:
  case ValidProgram(program: Program)
  case InvalidProgram(errors: List[String])

/**
 *  Validates a given program for these conditions:
 *  - A jump is not out-of-bounds.
 */
object Validator {

  def validate(program: Program) : ValidatorResult = {
    val instructions = program.instructions

    val errors = instructions.zipWithIndex.collect {
      case (Jnz(LiteralOperand(t)), idx) if (t >> 1) > instructions.size =>
        s"ValidationError: Invalid jump to $t in instruction at index ${idx << 1}."
    }

    if (errors.isEmpty) ValidatorResult.ValidProgram(program)
    else ValidatorResult.InvalidProgram(errors)
  }
}

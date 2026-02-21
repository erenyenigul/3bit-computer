package lang

import computer.Operands.{ComboOperand, LiteralOperand}


/**
 * 3-bit Computer Instruction set.
 *
 * Includes an extra instruction named End. This is appended by parser to the end of every program.
 * It exists just to simplify the Computer logic. Nothing crucial.
 */
enum Instruction:
  /**
   * performs division. The numerator is the value in the X register. The denominator is found by raising 2 to the power of the instruction’s combo operand. (So, an operand of 2 would divide X by 4 (2^2); an operand of 5 would divide X by 2^Y.) The result of the division operation is truncated to an integer and written to the X register.
   */
  case Xdv(rand: ComboOperand)

  /**
   * calculates the bitwise XOR of register Y and the instruction’s literal operand, then stores the result in register Y (overwrites the previous value).
   */
  case Yxl(rand: LiteralOperand)

  /**
   * calculates the value of its combo operand modulo 8 (thereby keeping only its lowest 3 bits), then writes that value to the Y register.
   */
  case Yst(rand: ComboOperand)

  /**
   * does nothing if the X register is 0. However, if the X register is not zero, it jumps by setting the instruction pointer to the value of its literal operand. If this instruction jumps, the instruction pointer is not increased by 2 after this instruction.
   */
  case Jnz(rand: LiteralOperand)

  /**
   * calculates the bitwise XOR of the register Y and register Z, then stores the result in register Y. For preserving consistency between all instructions, this instruction reads an operand but ignores it, i.e. the instruction pointer still needs to be increased by 2 after the instruction is executed.
   */
  case Yxz

  /**
   * calculates the value of its combo operand modulo 8, then outputs that value.
   */
  case Out(rand: ComboOperand)

  /**
   * works exactly like the xdv instruction except that the result is stored in the Y register. The numerator is still read from the X register. As is the case for the xdv instruction, the operand of the ydv instruction is also a combo operand.
   */
  case Ydv(rand: ComboOperand)

  /**
   * works exactly like the xdv instruction except that the result is stored in the Z register. The numerator is still read from the X register. As is the case for the xdv instruction, the operand of the zdv instruction is also a combo operand.
   */
  case Zdv(rand: ComboOperand)

  /**
   * halts the execution
   */
  case End  // additional instruction to denote end of program.
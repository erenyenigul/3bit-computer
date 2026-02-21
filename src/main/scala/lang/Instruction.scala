package lang

import computer.Operands.{ComboOperand, LiteralOperand}


/**
 * 3-bit Computer Instruction set.
 *
 * Includes an extra instruction named End. This is appended by parser to the end of every program.
 * It exists just to simplify the Computer logic. Nothing crucial.
 */
enum Instruction:
  case Xdv(rand: ComboOperand) 
  case Yxl(rand: LiteralOperand) 
  case Yst(rand: ComboOperand) 
  case Jnz(rand: LiteralOperand) 
  case Yxz
  case Out(rand: ComboOperand)
  case Ydv(rand: ComboOperand) 
  case Zdv(rand: ComboOperand) 

  case End  // additional instruction to denote end of program.
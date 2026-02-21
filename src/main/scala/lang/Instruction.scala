package lang

import computer.Precision.{ComboOperand, LiteralOperand}

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
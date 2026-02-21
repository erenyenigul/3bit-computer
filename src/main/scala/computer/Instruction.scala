package computer

import computer.Precision.{ComboOperand, LiteralOperand}

class Instruction
case class Xdv(rand: ComboOperand) extends Instruction
case class Yxl(rand: LiteralOperand) extends Instruction
case class Yst(rand: ComboOperand) extends Instruction
case class Jnz(rand: LiteralOperand) extends Instruction
case class Yxz() extends Instruction
case class Out(rand: ComboOperand) extends Instruction
case class Ydv(rand: ComboOperand) extends Instruction
case class Zdv(rand: ComboOperand) extends Instruction

case class End() extends Instruction // additional instruction to denote end of program.
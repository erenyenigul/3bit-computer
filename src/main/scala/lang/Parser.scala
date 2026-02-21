package lang

import computer.Precision.*
import computer.*
import lang.Parser.repsep

import scala.util.parsing.combinator.*

object Parser extends RegexParsers {

  override def skipWhitespace = true

  private def literalOperand: Parser[LiteralOperand] =
    "[0-7]".r ^^ { n => LiteralOperand(n.toInt) }

  private def comboOperand: Parser[ComboOperand] =
    "[0-6]".r ^^ { n => ComboOperand(n.toInt) }

  private def op[T, I](opcode: String, operand: Parser[I], constructor: I => T): Parser[T] = {
    opcode ~ "," ~ operand ^^ { case _ ~ _ ~ rand => constructor(rand) }
  }

  private def xdv: Parser[Xdv] = op("0", comboOperand, Xdv(_))
  private def yxl: Parser[Yxl] = op("1", literalOperand, Yxl(_))
  private def yst: Parser[Yst] = op("2", comboOperand, Yst(_))
  private def jnz: Parser[Jnz] = op("3", literalOperand, Jnz(_))
  private def yxz: Parser[Yxz] = "4" ~ "," ~ literalOperand ^^^ Yxz()
  private def out: Parser[Out] = op("5", comboOperand, Out(_))
  private def ydv: Parser[Ydv] = op("6", comboOperand, Ydv(_))
  private def zdv: Parser[Zdv] = op("7", comboOperand, Zdv(_))

  private def instruction: Parser[Instruction] = xdv | yxl | yst | jnz | yxz | out | ydv | zdv

  def program: Parser[Program] = {
    repsep(instruction, ",") ^^ { is => Program(is :+ End()) }
  }
}

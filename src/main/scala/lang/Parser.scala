package lang

import computer.Operands.*
import lang.Instruction.*
import lang.Parser.repsep

import scala.util.parsing.combinator.*

/**
 * A special parser for the specified language.
 * I could have just split the given strings and start executing the code right away.
 * However, I wanted to create something that is maintainable and extendable, even though it is just for an interview.
 *
 * Does not match every integer. For literals, it looks for numbers from 0-7, and for combos, it looks for 0-6.
 * Puts every instruction to its own enum.
 */

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
  private def yxz: Parser[Yxz.type] = "4" ~ "," ~ literalOperand ^^^ Yxz
  private def out: Parser[Out] = op("5", comboOperand, Out(_))
  private def ydv: Parser[Ydv] = op("6", comboOperand, Ydv(_))
  private def zdv: Parser[Zdv] = op("7", comboOperand, Zdv(_))

  private def instruction: Parser[Instruction] = xdv | yxl | yst | jnz | yxz | out | ydv | zdv

  def program: Parser[Program] = {
    repsep(instruction, ",") ^^ { is => Program(is :+ End) }
  }
}

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

  private def xdv: Parser[Xdv] = {
    "0" ~ "," ~ comboOperand ^^ {
      case _ ~ _ ~ rand => Xdv(rand)
    }
  }

  private def yxl: Parser[Yxl] = {
    "1" ~ "," ~ literalOperand ^^ {
      case _ ~ _ ~ rand => Yxl(rand)
    }
  }

  private def yst: Parser[Yst] = {
    "2" ~ "," ~ comboOperand ^^ {
      case _ ~ _ ~ rand => Yst(rand)
    }
  }

  private def jnz: Parser[Jnz] = {
    "3" ~ "," ~ literalOperand ^^ {
      case _ ~ _ ~ rand => Jnz(rand)
    }
  }

  private def yxz: Parser[Yxz] = {
    "4" ~ "," ~ literalOperand ^^ {
      case _ ~ _ ~ rand => Yxz()
    }
  }

  private def out: Parser[Out] = {
    "5" ~ "," ~ comboOperand ^^ {
      case _ ~ _ ~ rand => Out(rand)
    }
  }

  private def ydv: Parser[Ydv] = {
    "6" ~ "," ~ comboOperand ^^ {
      case _ ~ _ ~ rand => Ydv(rand)
    }
  }

  private def zdv: Parser[Zdv] = {
    "7" ~ "," ~ comboOperand ^^ {
      case _ ~ _ ~ rand => Zdv(rand)
    }
  }

  private def instruction: Parser[Instruction] = xdv | yxl | yst | jnz | yxz | out | ydv | zdv

  def program: Parser[Program] = {
    repsep(instruction, ",") ^^ { is => Program(is :+ End()) }
  }
}

import computer.Operands.{ComboOperand, LiteralOperand}
import lang.Instruction.*
import lang.{Parser, Program}
import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite {

  def assertShouldParse(code: String, expected: Program): Unit = {
    Parser.parseAll(Parser.program, code) match {
      case Parser.Success(program, _) =>
        assert(program == expected)
      case _ =>
        fail(s"Parser failed to process the code: $code")
    }
  }

  def assertShouldFailParse(code: String): Unit = {
    val result = Parser.parseAll(Parser.program, code)

    assert(!result.successful, s"Parser should have failed for $code.")
  }

  test("5,0,5,1,5,4 should be parsed") {
    assertShouldParse(
      "5,0,5,1,5,4",
      Program(
        Out(ComboOperand(0)) :: Out(ComboOperand(1)) :: Out(ComboOperand(4)) :: End :: Nil
      )
    )
  }

  test("0,1,5,4,3,0 should be parsed") {
    assertShouldParse(
      "0,1,5,4,3,0",
      Program(
        Xdv(ComboOperand(1)) :: Out(ComboOperand(4)) :: Jnz(LiteralOperand(0)) :: End :: Nil
      )
    )
  }

  test("1,7 should be parsed") {
    assertShouldParse(
      "1,7",
      Program(
        Yxl(LiteralOperand(7)) :: End :: Nil
      )
    )
  }

  test("4,0 should be parsed") {
    assertShouldParse(
      "4,0",
      Program(
        Yxz :: End :: Nil
      )
    )
  }

  test("Combo argument can't be 7") {
    assertShouldFailParse("0,7")
  }
}

import computer.{Computer, State}
import lang.Parser
import org.scalatest.compatible.Assertion
import org.scalatest.funsuite.AnyFunSuite

class IntegrationTest extends AnyFunSuite {

  private def assertParseAndRunResultEquals(code: String, state: State, expected: String): Assertion = {
    Parser.parseAll(Parser.program, code) match {
      case Parser.Success(program, _) =>
        val computer = Computer(program, state)

        assert(computer.run() == expected)
      case Parser.Failure(msg, _) =>
        fail(s"Parser failed to process the code: $code with message: $msg")

      case Parser.Error(msg, _) =>
        fail(s"Parser failed to process the code: $code with message: $msg")
    }
  }

  test("Starting state 1") {
    assertParseAndRunResultEquals(
      code="0,1,5,4,3,0",
      state=State(3729, 0, 0),
      expected="0,4,2,1,4,2,5,6,7,3,1,0"
    )
  }

  test("Starting state 2") {
    assertParseAndRunResultEquals(
      code = "0,3,5,4,3,0",
      state = State(8642024, 0, 0),
      expected = "5,7,6,5,7,0,4,0"
    )
  }

  test("Example 1 of instruction operations") {
    assertParseAndRunResultEquals(
      code = "2,6",
      state = State(0, 0, 9),
      expected = ""
    )
  }

  test("Example 2 of instruction operations") {
    assertParseAndRunResultEquals(
      code = "5,0,5,1,5,4",
      state = State(10, 0, 0),
      expected = "0,1,2"
    )
  }

  test("Example 3 of instruction operations") {
    assertParseAndRunResultEquals(
      code = "0,1,5,4,3,0",
      state = State(2024, 0, 0),
      expected = "4,2,5,6,7,7,7,7,3,1,0"
    )
  }

  test("Extra example: out of bounds jump should not crash") {
    assertParseAndRunResultEquals(
      code = "3,6",
      state = State(1, 0, 0),
      expected = ""
    )
  }
}

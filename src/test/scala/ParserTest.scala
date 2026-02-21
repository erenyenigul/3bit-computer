import lang.Parser
import org.scalatest.funsuite.AnyFunSuite

class ParserTest extends AnyFunSuite {

  def assertShouldParse(in: String): Unit = {
    assert(Parser.parseAll(Parser.program, in).successful)
  }

  test("5,0,5,1,5,4 should be parsed") {
    assertShouldParse("5,0,5,1,5,4")
  }

  test("0,1,5,4,3,0 should be parsed") {
    assertShouldParse("0,1,5,4,3,0")
  }

  test("1,7 should be parsed") {
    assertShouldParse("1,7")
  }
  
  test("4,0 should be parsed") {
    assertShouldParse("4,0")
  }
}

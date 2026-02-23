import computer.{Computer, State}
import disassembler.Disassembler
import lang.Parser

/**
 * Executes a given code with initial register values. Parses the program, and then creates a `Computer` instance for execution.
 *
 * @param x initial register x value
 * @param y initial register y value
 * @param z initial register z value
 * @param code to execute
 */
@main def main(x: Int, y: Int, z: Int, code: String): Unit = {
  Parser.parseAll(Parser.program, code) match {
    case failure: Parser.NoSuccess => println(s"${failure.next.pos.longString}\nParserError: ${failure.msg}\n")
    case Parser.Success(program, _) =>
      val state = State(x, y, z)

      val computer = Computer(
        program,
        state
      )

      println(computer.run())
  }
}

/**
 * Runs disassembler for better-understanding of a given code.
 * e.g. program 0,1,5,4,3,0 turns into
 * ```
 * _entry:
 *  xdv combo.1
 *  out combo.4
 *  jnz literal.0
 *  end
 * ```
 * @param code to disassemble
 */
@main def disassemble(code: String): Unit = {
  Parser.parseAll(Parser.program, code) match {
    case failure: Parser.NoSuccess => println(s"${failure.next.pos.longString}\nParserError: ${failure.msg}\n")
    case Parser.Success(program, _) =>
      val disassembly = Disassembler.run(program)

      println(disassembly)
  }
}
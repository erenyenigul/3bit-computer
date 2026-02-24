import computer.{Computer, State}
import disassembler.Disassembler
import lang.ValidatorResult.{InvalidProgram, ValidProgram}
import lang.{Parser, Program, Validator}

def parse(code: String): Either[String, Program] = {
    Parser.parseAll(Parser.program, code) match {
      case failure: Parser.NoSuccess => Left(s"${failure.next.pos.longString}\nParserError: ${failure.msg}\n")
      case Parser.Success(program, _) => Right(program)
    }
}

def validate(program: Program): Either[String, Program] = {
  Validator.validate(program) match {
    case InvalidProgram(errors) => Left(errors.mkString("\n"))
    case ValidProgram(p) => Right(p)
  }
}

/**
 * Executes a given code with initial register values. Parses the program, validates it, and then creates a `Computer` instance for execution.
 *
 * @param x initial register x value
 * @param y initial register y value
 * @param z initial register z value
 * @param code to execute
 */
@main def main(x: Int, y: Int, z: Int, code: String): Unit = {
  val result = for {
    program <- parse(code)
    validated <- validate(program)
  } yield {
      val state = State(x, y, z)

      val computer = Computer(
        program,
        state
      )

      computer.run()
  }

  println(result.merge)
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
  val result = for {
    program <- parse(code)
    validated <- validate(program)
  } yield {
    Disassembler.run(program)
  }

  println(result.merge)
}
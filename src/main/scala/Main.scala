import computer.{Computer, State}
import disassembler.Disassembler
import lang.Parser

@main def main(x: Int, y: Int, z: Int, code: String): Unit = {
  Parser.parseAll(Parser.program, code) match {
    case Parser.Failure(msg, _) => println(s"Error when parsing: $msg")
    case Parser.Error(msg, _) => println(s"Error when parsing: $msg")

    case Parser.Success(program, _) =>
      val state = State(x, y, z)

      val computer = Computer(
        program,
        state
      )

      println(computer.run())
  }
}

@main def disassemble(code: String): Unit = {
  Parser.parseAll(Parser.program, code) match {
    case Parser.Failure(msg, _) => println(s"Error when parsing: $msg")
    case Parser.Error(msg, _) => println(s"Error when parsing: $msg")

    case Parser.Success(program, _) =>
      val disassembly = Disassembler.run(program)

      println(disassembly)
  }
}
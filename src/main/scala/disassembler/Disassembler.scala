package disassembler

import lang.Program
import lang.Instruction.*

object Disassembler {

  def run(program: Program) : String = {
    val builder = StringBuilder()

    builder ++= "_entry:\n"
    for (instruction <- program.instructions) {
      builder ++= "\t"

      instruction match {
        case Xdv(rand) =>
          builder ++= "xdv "
          builder ++= s"combo.${rand}"

        case Yxl(rand) =>
          builder ++= "yxl "
          builder ++= s"literal.${rand}"

        case Yst(rand) =>
          builder ++= "yst "
          builder ++= s"combo.${rand}"

        case Jnz(rand) =>
          builder ++= "jnz "
          builder ++= s"literal.${rand}"

        case Yxz =>
          builder ++= "yxz"


        case Out(rand) =>
          builder ++= "out "
          builder ++= s"combo.${rand}"

        case Ydv(rand) =>
          builder ++= "ydv "
          builder ++= s"combo.${rand}"

        case Zdv(rand) =>
          builder ++= "zdv "
          builder ++= s"combo.${rand}"

        case End =>
          builder ++= "end"
      }

      builder ++= "\n"
    }

    builder.toString()
  }
}

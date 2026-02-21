package computer

object Precision {
  opaque type ComboOperand = Int
  opaque type LiteralOperand = Int

  object LiteralOperand:
    def apply(n: Int): LiteralOperand = {
      require(n >= 0 && n <= 7, s"Value $n is out of 3-bit range.")
      n
    }
    def unapply(l: LiteralOperand): Some[Int] = Some(l)

  object ComboOperand:
    def apply(n: Int): ComboOperand = {
      require(n >= 0 && n <= 6, s"Value $n is out of [0, 6] range.")
      n
    }
    def unapply(c: ComboOperand): Some[Int] = Some(c)
}

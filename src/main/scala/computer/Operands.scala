package computer

/**
 * Opaque types for 3-bit and [0,6]-ranged operands. Introduces opaque-types for `LiteralOperand` and `ComboOperand`
 */

object Operands {
  opaque type ComboOperand = Int
  opaque type LiteralOperand = Int

  /**
   * Literal operands are 3-bit integers.
   */
  object LiteralOperand:
    def apply(n: Int): LiteralOperand = {
      require(n >= 0 && n <= 7, s"Value $n is out of 3-bit range.")
      n
    }
    def unapply(l: LiteralOperand): Some[Int] = Some(l)

  /**
   * Combo operands are integers in the range [0,6].
   */
  object ComboOperand:
    def apply(n: Int): ComboOperand = {
      require(n >= 0 && n <= 6, s"Value $n is out of [0, 6] range.")
      n
    }
    def unapply(c: ComboOperand): Some[Int] = Some(c)
}

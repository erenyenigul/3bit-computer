package lang

/**
 * contains a list of instructions. Could be extended with metadata fields if optimization passes are introduced (highly unlikely :)).
 * @param instructions of program
 */
case class Program (instructions: List[Instruction])
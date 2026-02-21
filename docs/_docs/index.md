# 3-Bit Computer Documentation

This guide aims to explain the details of the 3-bit computer project. You can also take a look at the API section to understand what each component does.

## Quick Overview

### Parser

Uses Scala Parser Combinators library. Instead of splitting the given text with comma, I wanted to use a parser generator.
This way it was easier to check if a given program is valid. E.g. when 7 is falsely provided as a combo operand.

We convert the given string into a list of `Instruction` class instances while parsing, and then forming a `Program` instance.

### Instruction and Program

`Instruction` is an enum. Each instruction has its operand with appropriate type.

`Program` is just a list of instructions.

### Opaque Types for Operands

Since operands are either 3-bits or in range [0,6], it is a good idea to create opaque types for them. That way some their properties are ensured, even without extra overhead. These opaque types are : `LiteralOperand` and `ComboOperand`

### State

is a class to store information about execution: registers, instruction pointer, output buffer.

### Computer

executes a given program with an initial state. Returns the output formed by the `out` instructions.

### Disassembler

is an extra component I implemented. Converts a given program into a custom-made assembly for better understanding of a given code.



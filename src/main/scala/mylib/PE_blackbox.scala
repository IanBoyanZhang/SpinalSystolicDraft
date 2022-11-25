package mylib

import spinal.core._

// Define a PE as a BlackBox
//class PE(wordWidth: Int, wordCount: Int) extends BlackBox {
class ProcessingElement(width: Int, dim: Int) extends BlackBox {
  // Add VHDL Generics / Verilog parameters to the blackbox
  // You can use String, Int, Double, Boolean, and all SpinalHDL base
  // types as generic values
  addGeneric("W", width)

  val i = MatIN(width, dim)
  val o = MatOUT(width, dim)

  //mapCurrentClockDomain(i.clk)
  noIoPrefix()
}

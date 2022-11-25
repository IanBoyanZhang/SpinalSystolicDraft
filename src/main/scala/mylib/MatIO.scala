package mylib

import spinal.core._

case class MatIN(width: Int, dim: Int) extends Bundle {
  val clk  = in(Bool()) 
  val rst  = in(Bool())
  val en   = in(Bool())
  val mode = in(Bool()) // mode = 1 write mode
  val A    = in(UInt(width * dim bits))
  val B    = in(UInt(width * dim bits))
}

case class MatOUT(width: Int, dim: Int) extends Bundle {
  val A    = out(UInt(width * dim bits))
  val B    = out(UInt(width * dim bits))
  val C    = out(UInt(width * dim bits))
}

case class PE_IO(width: Int, dim: Int) extends Bundle {
  val a = UInt(width * dim * 2 bits) 
  val b = UInt(width * dim * 2 bits)
}

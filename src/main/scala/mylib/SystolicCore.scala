package mylib

import spinal.core._

class SystolicCore(width: Int, dim: Int) extends Component {
  val io = new Bundle {
    val i        = MatIN(width, dim)
    val o        = MatOUT(width, dim * dim)
    // Using pull
    // val debug_pe = out(PE_IO(width, dim))
  }

  noIoPrefix()

  val PE = Array.fill(dim)(Array.fill(dim)(new ProcessingElement(width, 1)))

  for (ix <- 0 until dim) {
    for (iy <- 0 until dim) {
      val idx = ix * dim + iy
      PE(ix)(iy).i.clk  := io.i.clk
      PE(ix)(iy).i.rst  := io.i.rst
      PE(ix)(iy).i.en   := io.i.en
      PE(ix)(iy).i.mode := io.i.mode
      io.o.C(width * (idx + 1) - 1 downto width * idx) := PE(ix)(iy).o.C 
    }
  }

  val A = Array.fill(dim)(UInt(width bits))
  val B = Array.fill(dim)(UInt(width bits))

  for (ix <- 0 until dim) {
    A(ix) = io.i.A((ix + 1) * width - 1 downto ix * width)
    B(ix) = io.i.B((ix + 1) * width - 1 downto ix * width)
  }

  for (ix <- 0 until dim) {
    PE(ix)(0).i.A := A(ix)
    PE(0)(ix).i.B := B(ix)
  }

  for (ix <- 0 until dim) {
    for (iy <- 1 until dim) {
      PE(ix)(iy).i.A := PE(ix)(iy - 1).o.A
      PE(iy)(ix).i.B := PE(iy - 1)(ix).o.B
    }
  }
}

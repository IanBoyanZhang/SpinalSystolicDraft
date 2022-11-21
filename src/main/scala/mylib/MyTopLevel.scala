package mylib

import spinal.core._

// Hardware definition
case class MyTopLevel() extends Component {
  val io = new Bundle {
    val i_clk = Bool()
    val i_rst = Bool()
  }

  // Remove io_ prefix
  noIoPrefix()

  // Create osc_clk clock domain
  val coreClockDomain = ClockDomain(
    clock  = io.i_clk,
    reset  = io.i_rst,
    config = ClockDomainConfig(
      clockEdge        = RISING,
      resetKind        = SYNC,
      resetActiveLevel = LOW
    ) 
  )

  // Create osc_clk clock area
  val coreArea = new ClockingArea(coreClockDomain) {
    val coreClockRegister = Reg(UInt(4 bits)) init(7)

    // ClockingArea and registers
    // reg := reg + 1;
    // io.result :=
  }

}



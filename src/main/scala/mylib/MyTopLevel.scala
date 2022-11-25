package mylib

import spinal.core._

// Hardware definition
case class MyTopLevel() extends Component {
  val io = new Bundle {
    val i_clk = in(Bool())
    val i_rst = in(Bool())
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
  val core = new ClockingArea(coreClockDomain) {
    // Add new generics?
    val u_systolic_core = new SystolicCore(width = 16, dim = 4)

    // I guess we have to use the coreClockDomain

    u_systolic_core.io.i.clk  := True
    u_systolic_core.io.i.rst  := True
    u_systolic_core.io.i.A    := 0
    u_systolic_core.io.i.B    := 0
    u_systolic_core.io.i.en   := True
    u_systolic_core.io.i.mode := True
  }

}



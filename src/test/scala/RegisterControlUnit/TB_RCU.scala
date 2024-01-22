package RegisterControlUnit

import chisel3._
import chiseltest._
import org.scalatest.flatspec.AnyFlatSpec


class TB_RCU extends AnyFlatSpec with ChiselScalatestTester {
  behavior of "TB_RCU"
  it should "Test RegisterControlUnit.RCU" in {
    val p = new RCU_Params (64)
    test(new RCU(p)).withAnnotations(Seq(WriteVcdAnnotation)) { dut =>

    //==========================================================
    // initialisation
    //==========================================================
    dut.io.rf_in.poke(0x01)
    dut.io.wb_valid.poke(false.B)
    dut.io.rf_out.expect(0x00)
    dut.clock.step(1) // clk+1

    dut.io.rf_in.poke(0x01)
    dut.io.wb_valid.poke(false.B)
    dut.io.rf_out.expect(0x00)
    

    }
  }
  it should "All tested!"
}
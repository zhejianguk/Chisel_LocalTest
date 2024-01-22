package RegisterControlUnit

import chisel3._
import chisel3.experimental.{BaseModule}
// import chisel3.stage.ChiselStage


//==========================================================
// Parameters
//==========================================================
case class RCU_Params (
  xLen: Int
)

//==========================================================
// I/Os
//==========================================================
class RCU_IO (params: RCU_Params) extends Bundle {
  val rf_in = Input(UInt(params.xLen.W))
  val rf_out = Output(UInt(params.xLen.W))
  val wb_valid = Input(Bool())
}


trait Has_RCU_IO extends BaseModule {
  val params: RCU_Params
  val io = IO(new RCU_IO(params))
}

//==========================================================
// Implementations
//==========================================================
class RCU (val params: RCU_Params) extends Module with Has_RCU_IO
{
  val rf_reg = RegInit(0.U(params.xLen.W))
  val counter = RegInit(0.U(32.W))

  counter := Mux(io.wb_valid, counter+1.U, counter)

  when (counter === 100.U) {
    rf_reg <= io.rf_in
  }

  io.rf_out := Mux(counter === 500.U, rf_reg, 0.U)
}
package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;

public class IntakeIOSim implements IntakeIO {
  private static final double LOOP_PERIOD_SECS = 0.02;

  // private final DCMotor intakeSim = DCMotor.getKrakenX60Foc(1);
  private final DCMotorSim intakeSim = null; // new DCMotorSim(DCMotor.getNeo550(1), 1, 0.025);
  // private final DCMotorSim intakeRightFeederSim = new DCMotorSim(DCMotor.getNeo550(1), 1, 0.025);
  // private final DCMotorSim intakeIndexerSim = new DCMotorSim(DCMotor.getNEO(1), 1, 0.004);

  private double intakeAppliedVolts = 0d;
  // private double intakeRightFeederAppliedVolts = 0d;
  // private double intakeIndexerAppliedVolts = 0d;

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    intakeSim.update(LOOP_PERIOD_SECS);
    // intakeRightFeederSim.update(LOOP_PERIOD_SECS);
    // intakeIndexerSim.update(LOOP_PERIOD_SECS);

    inputs.intakeAppliedVolts = intakeAppliedVolts;
    inputs.intakeRadPerSec = intakeSim.getAngularVelocityRadPerSec();
    inputs.intakeCurrentAmps = new double[] {Math.abs(intakeSim.getCurrentDrawAmps())};
  }

  @Override
  public void setIntakeVoltage(double volts) {
    intakeAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
    // intakeRightFeederAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);

    intakeSim.setInputVoltage(intakeAppliedVolts);
    // intakeRightFeederSim.setInputVoltage(intakeRightFeederAppliedVolts);
  }

  // @Override
  // public void setIndexerVoltage(double volts) {
  //     intakeIndexerAppliedVolts = MathUtil.clamp(volts, -12.0, 12.0);
  //     intakeIndexerSim.setInputVoltage(intakeIndexerAppliedVolts);
  // }

  @Override
  public void stop() {
    intakeAppliedVolts = 0;
    // intakeRightFeederAppliedVolts = 0;
    // intakeIndexerAppliedVolts = 0;

    intakeSim.setInputVoltage(intakeAppliedVolts);
    // intakeRightFeederSim.setInputVoltage(intakeRightFeederAppliedVolts);
    // intakeIndexerSim.setInputVoltage(intakeIndexerAppliedVolts);
  }
}

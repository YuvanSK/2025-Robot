package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.intake.IntakeIO.IntakeIOInputs;

public class Intake extends SubsystemBase {
  private final IntakeIO io;
  private final IntakeIOInputs inputs = new IntakeIOInputs(); // used to have autologged

  private double targetSpeed = 0;
  /// private double targetFeederSpeed = 0;

  public Intake(IntakeIO io) {
    this.io = io;
    new SlewRateLimiter(.4);
  }

  private void setSpeedRaw(double speed) {
    speed = MathUtil.clamp(speed, -1, 1);
    io.setIntakeVoltage(speed * Constants.IntakeConstants.maxIntakeMotorVoltage);
  }

  // private void setFeederSpeedRaw(double speed) {
  //     speed = MathUtil.clamp(speed, -1, 1);
  //     io.setFeederVoltage(speed * Constants.IntakeConstants.maxIntakeMotorVoltage);
  // }

  public void setSpeed(double speed) {
    targetSpeed = speed;
  }

  // public void setFeederSpeed(double speed) {
  //     targetFeederSpeed = speed;
  // }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    // Logger.processInputs("Intake", inputs);    PLEASE CHECK THIS AGAIN??

    setSpeedRaw(targetSpeed);
    // setIndexerSpeedRaw(targetIndexerSpeed);
  }

  public void stop() {
    io.stop();
  }
}

// Copyright (c) 2025 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.subsystems.intake;

// import static org.littletonrobotics.frc2025.util.PhoenixUtil.tryUntilOk;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

/** Generic roller IO implementation for a roller or series of rollers using a Kraken. */
public class IntakeIOTalonFX implements IntakeIO {
  private final TalonFX talon;

  //   private final StatusSignal<Angle> position;
  //   private final StatusSignal<AngularVelocity> velocity;
  //   private final StatusSignal<Voltage> appliedVoltage;
  //   private final StatusSignal<Current> supplyCurrent;
  //   private final StatusSignal<Current> torqueCurrent;
  //   private final StatusSignal<Temperature> tempCelsius;

  // Single shot for voltage mode, robot loop will call continuously
  private final VoltageOut voltageOut = new VoltageOut(0.0).withEnableFOC(true).withUpdateFreqHz(0);
  private final NeutralOut neutralOut = new NeutralOut();

  public IntakeIOTalonFX(int id, String bus) {
    talon = new TalonFX(60, bus); // CHANGE ID, NOT ACCURATE; send this a string later

    TalonFXConfiguration config = new TalonFXConfiguration();
    // config.MotorOutput.Inverted = invert ? InvertedValue.Clockwise_Positive :
    // InvertedValue.CounterClockwise_Positive;
    // config.MotorOutput.NeutralMode = brake ? NeutralModeValue.Brake : NeutralModeValue.Coast;
    // config.CurrentLimits.SupplyCurrentLimit = currentLimitAmps;
    config.CurrentLimits.SupplyCurrentLimitEnable = true;
    // tryUntilOk(5, () -> talon.getConfigurator().apply(config));

    // position = talon.getPosition();
    // velocity = talon.getVelocity();
    // appliedVoltage = talon.getMotorVoltage();
    // supplyCurrent = talon.getSupplyCurrent();
    // torqueCurrent = talon.getTorqueCurrent();
    // tempCelsius = talon.getDeviceTemp();

    // tryUntilOk(
    //     5,
    //     () ->
    //         BaseStatusSignal.setUpdateFrequencyForAll(
    //             50.0,
    //             position,
    //             velocity,
    //             appliedVoltage,
    //             supplyCurrent,
    //             torqueCurrent,
    //             tempCelsius));
    // tryUntilOk(5, () -> talon.optimizeBusUtilization(0, 1.0));
  }

  @Override
  public void updateInputs(IntakeIOInputs inputs) {
    inputs.intakeAppliedVolts =
        talon.getDutyCycle().getValueAsDouble() * talon.getSupplyVoltage().getValueAsDouble();
  }

  @Override
  public void runVolts(double volts) {
    talon.setControl(voltageOut.withOutput(volts));
  }

  @Override
  public void stop() {
    talon.setControl(neutralOut);
  }
}

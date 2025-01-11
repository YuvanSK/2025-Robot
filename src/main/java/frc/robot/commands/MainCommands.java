package frc.robot.commands;

import static edu.wpi.first.wpilibj2.command.Commands.*;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class MainCommands {

  private MainCommands() {}

  public static Command stopIntake(Intake intake) {
    return runOnce(
        () -> {
          intake.setSpeed(0);
        },
        intake);
  }

  public static Command runIntake(Intake intake) {
    return runOnce(
        () -> {
          intake.setSpeed(0.1); // SPEED IS NOT CORRECT, CHECK AGAIN
        },
        intake);
  }

  public static Command runOuttake(Intake intake) {
    return runOnce(
        () -> {
          intake.setSpeed(-0.1); // SPEED IS NOT CORRECT, CHECK AGAIN
        },
        intake);
  }
}

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    Drivetrain drivetrain;
    CommandXboxController controller;
    Joystick rightJoystick;

    public Drive(Drivetrain drivetrain, CommandXboxController controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    private double inputToSpeed(double input) {
        if (Math.abs(input) < Constants.DriveConstants.kJoystickDeadzone) return 0;
        double speed = input * Constants.DriveConstants.kMotorWheelSpeedScalar;
        if (Constants.DriveConstants.kMotorSpeedInverse) {
            speed *= -1;
        }
        return speed;
    }

    @Override
    public void execute() {
        double leftSpeed = inputToSpeed(controller.getRawAxis(Constants.GamepadConstants.kLeftJoystickAxis));
        double rightSpeed = -inputToSpeed(controller.getRawAxis(Constants.GamepadConstants.kRightJoystickAxis));
        drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}

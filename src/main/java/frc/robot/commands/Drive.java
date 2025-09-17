package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.Drivetrain;

public class Drive extends Command {
    Drivetrain drivetrain;
    GenericHID controller;
    Joystick rightJoystick;

    public Drive(Drivetrain drivetrain, GenericHID controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    private double inputToSpeed(double input) {
        if (Math.abs(input) < OperatorConstants.kGamepadJoystickDeadzone) return 0;
        double speed = input * OperatorConstants.kGamepadJoystickScalar;
        if (OperatorConstants.kGamepadJoystickInverse) {
            speed *= -1;
        }
        return speed;
    }

    @Override
    public void execute() {
        double leftSpeed = -inputToSpeed(controller.getRawAxis(OperatorConstants.kGamepadLeftJoystickAxisY));
        double rightSpeed = inputToSpeed(controller.getRawAxis(OperatorConstants.kGamepadRightJoystickAxisY));
        drivetrain.tankDrive(leftSpeed, rightSpeed);
    }
}

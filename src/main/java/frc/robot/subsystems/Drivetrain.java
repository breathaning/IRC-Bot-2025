package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
    private TalonFX leftMotor, rightMotor;

    public Drivetrain() {
        leftMotor = new TalonFX(Constants.DrivetrainConstants.kLeftMotorId);
        rightMotor = new TalonFX(Constants.DrivetrainConstants.kRightMotorId);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
    public static final TalonFX leftMotor = new TalonFX(DrivetrainConstants.kLeftMotorId);
    public static final TalonFX rightMotor = new TalonFX(DrivetrainConstants.kRightMotorId);

    public Drivetrain() {

    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class Drivetrain extends SubsystemBase {
    private final TalonFX leftMotor = new TalonFX(DrivetrainConstants.kLeftMotorId);
    private final TalonFX rightMotor = new TalonFX(DrivetrainConstants.kRightMotorId);
    {
        TalonFXConfiguration config = new TalonFXConfiguration();
        config.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
        rightMotor.getConfigurator().apply(config);
    }

    public Drivetrain() {
        
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftMotor.set(leftSpeed);
        rightMotor.set(rightSpeed);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.ShooterState;

public class Shooter extends SubsystemBase {
    public static final TalonFX launchMotor = new TalonFX(ShooterConstants.kLaunchMotorId);
    public static final SparkMax feedMotor = new SparkMax(ShooterConstants.kFeedMotorId, MotorType.kBrushless);

    public static final TalonFXConfiguration launcherConfig = new TalonFXConfiguration();
    {   
        // idk how to tune values
        // this is straight from the documentation
        launcherConfig.Slot0.kS = 0.25;
        launcherConfig.Slot0.kV = 0.12;
        launcherConfig.Slot0.kA = 0.01;
        launcherConfig.Slot0.kP = 4.8;
        launcherConfig.Slot0.kI = 0;
        launcherConfig.Slot0.kD = 0.1;
        launcherConfig.MotionMagic.MotionMagicCruiseVelocity = 10.0;
        launcherConfig.MotionMagic.MotionMagicAcceleration = 10.0;

        launchMotor.getConfigurator().apply(launcherConfig);
    }
    
    public Shooter() {
        
    }

    public void setState(ShooterState shooterState) {
        setLaunchSpeed(shooterState.launchSpeed);
        setFeedSpeed(shooterState.feedSpeed);
    }

    public void setLaunchSpeed(double speed) {
        launchMotor.set(speed);
    }

    public void setFeedSpeed(double speed) {
        feedMotor.set(speed);
    }
}

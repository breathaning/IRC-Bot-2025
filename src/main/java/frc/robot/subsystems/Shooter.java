package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    public static final TalonFX launcherMotor = new TalonFX(ShooterConstants.kLauncherMotorId);
    public static final SparkMax feederMotor = new SparkMax(ShooterConstants.kFeederMotorId, MotorType.kBrushless);

    public static final TalonFXConfiguration launcherConfig = new TalonFXConfiguration();
    {   
        // idk how to tune values
        launcherConfig.Slot0.kP = 0.15;
        launcherConfig.Slot0.kI = 0;
        launcherConfig.Slot0.kD = 0.5;
        launcherConfig.MotionMagic.MotionMagicCruiseVelocity = 10.0;
        launcherConfig.MotionMagic.MotionMagicAcceleration = 100.0;
        launcherConfig.MotionMagic.MotionMagicJerk = 200.0;

        launcherMotor.getConfigurator().apply(launcherConfig);
    }
    
    public Shooter() {
        
    }

    public void setSpeed(double speed) {
        launcherMotor.set(speed);
        feederMotor.set(speed);
    }

    public void setLauncherSpeed(double speed) {
        launcherMotor.set(speed);
    }

    public void setFeederSpeed(double speed) {
        feederMotor.set(speed);
    }
}

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    private static final TalonFX launcherMotor = new TalonFX(ShooterConstants.kLauncherMotorId);
    private static final SparkMax feederMotor = new SparkMax(ShooterConstants.kFeederMotorId, MotorType.kBrushless);
    
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

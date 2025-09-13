package frc.robot.subsystems;

import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class Shooter extends SubsystemBase {
    public static final TalonFX launcherMotor = new TalonFX(ShooterConstants.kLauncherMotorId);
    public static final SparkMax feederMotor = new SparkMax(ShooterConstants.kFeederMotorId, MotorType.kBrushless);

    
    public static final SlotConfigs launcherConfig = new SlotConfigs();
    {   
        // idk how to tune values
        launcherConfig.kS = 0.24;
        launcherConfig.kV = 0.12;
        launcherConfig.kP = 4.8; 
        launcherConfig.kI = 0.1; 
        launcherConfig.kD = 0; 
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

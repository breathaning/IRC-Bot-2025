package frc.robot.subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.ShooterState;

public class Shooter extends SubsystemBase {
    private final TalonFX launchMotor = new TalonFX(ShooterConstants.kLaunchMotorId);
    private final SparkMax feedMotor = new SparkMax(ShooterConstants.kFeedMotorId, MotorType.kBrushless);

    private final TalonFXConfiguration launcherConfig = new TalonFXConfiguration();
    private final VelocityVoltage velocityVoltage = new VelocityVoltage(0).withSlot(0);
    {   
        // idk how to tune values
        // this is straight from the documentation
        launcherConfig.Slot0.kS = 0;
        launcherConfig.Slot0.kV = 0;
        launcherConfig.Slot0.kA = 0;
        launcherConfig.Slot0.kP = 0.1;
        launcherConfig.Slot0.kI = 0;
        launcherConfig.Slot0.kD = 0.1;
        // launcherConfig.MotionMagic.MotionMagicCruiseVelocity = 10.0;
        // launcherConfig.MotionMagic.MotionMagicAcceleration = 10.0;

        launchMotor.getConfigurator().apply(launcherConfig);
    }
    
    public Shooter() {
        
    }

    public void setState(ShooterState shooterState) {
        setLaunchSpeed(shooterState.launchSpeed);
        setFeedSpeed(shooterState.feedSpeed);
    }

    public void setLaunchSpeed(double speed) {
        launchMotor.setControl(velocityVoltage.withVelocity(speed));
    }

    public void setFeedSpeed(double speed) {
        feedMotor.set(speed);
    }

    public double getLaunchSpeed() {
        return launchMotor.getClosedLoopError().getValueAsDouble();
    }
}

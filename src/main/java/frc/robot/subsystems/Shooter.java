package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private TalonFX shooterMotor;
    
    public Shooter() {
        shooterMotor = new TalonFX(0);
    }

    public void setSpeed(double speed) {
        shooterMotor.set(speed);
    }
}

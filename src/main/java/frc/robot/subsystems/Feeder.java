package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
    SparkMax feederMotor;
    public Feeder() {
        feederMotor = new SparkMax(1, MotorType.kBrushless);
    }

    public void setSpeed(double speed) {
        feederMotor.set(speed);
    }
}

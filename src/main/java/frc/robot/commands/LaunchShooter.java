package frc.robot.commands;

import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class LaunchShooter extends Command {
    Shooter shooter;


    public LaunchShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setLauncherSpeed(0.5);
        shooter.setFeederSpeed(0);
    }

    @Override
    public void execute() {
        // maybe just use the builtin wpi stuff idk what im doing 
        Shooter.launcherMotor.setControl(new MotionMagicVelocityVoltage(0.2).withSlot(0));
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setSpeed(0);
    }
}

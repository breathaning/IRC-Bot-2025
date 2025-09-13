package frc.robot.commands;

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
        shooter.setLauncherSpeed(1);
        shooter.setFeederSpeed(0.1);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setSpeed(0);
    }
}

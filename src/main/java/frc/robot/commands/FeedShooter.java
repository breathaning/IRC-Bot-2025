package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class FeedShooter extends Command {
    
    Shooter shooter;

    public FeedShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setSpeed(-0.2);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setSpeed(0);
    }
}

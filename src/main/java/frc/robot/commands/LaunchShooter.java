package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.ShooterState;
import frc.robot.subsystems.Shooter;

public class LaunchShooter extends Command {
    Shooter shooter;

    public LaunchShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setFeedSpeed(0);
        shooter.setLaunchSpeed(ShooterState.LAUNCH.feedSpeed);
    }

    @Override
    public void execute() {
        double launcherVelocity = shooter.getLaunchSpeed();
        if (launcherVelocity / ShooterState.LAUNCH.launchSpeed >= ShooterConstants.kRevLaunchThreshold) {
            shooter.setFeedSpeed(ShooterState.LAUNCH.feedSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(ShooterState.IDLE);
    }
}

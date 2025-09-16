package frc.robot.commands;

import com.ctre.phoenix6.controls.MotionMagicVelocityVoltage;
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
        shooter.setLaunchSpeed(0);
        shooter.setFeedSpeed(0);
        Shooter.launchMotor.setControl(new MotionMagicVelocityVoltage(ShooterState.LAUNCH.launchSpeed));
    }

    @Override
    public void execute() {
        double launcherVelocity = Shooter.launchMotor.getClosedLoopError().getValueAsDouble();
        if (launcherVelocity / ShooterState.LAUNCH.launchSpeed >= ShooterConstants.kRevLaunchThreshold) {
            shooter.setFeedSpeed(ShooterState.LAUNCH.feedSpeed);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(ShooterState.IDLE);
    }
}

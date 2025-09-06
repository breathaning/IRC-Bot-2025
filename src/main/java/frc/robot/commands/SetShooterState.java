package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants.ShooterState;
import frc.robot.subsystems.Shooter;

public class SetShooterState extends Command {
    private Shooter shooter;
    private ShooterState shooterState;

    public SetShooterState(Shooter shooter, ShooterState shooterState) {
        this.shooter = shooter;
        this.shooterState = shooterState;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setSpeed(shooterState.motorSpeed);
    }

    @Override
    public void end(boolean i) {
        shooter.setSpeed(ShooterState.IDLE.motorSpeed);
    }
}

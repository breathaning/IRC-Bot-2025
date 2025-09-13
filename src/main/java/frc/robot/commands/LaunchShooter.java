package frc.robot.commands;

import com.ctre.phoenix6.controls.PositionVoltage;

import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;

public class LaunchShooter extends Command {
    Shooter shooter;

    final TrapezoidProfile.State shooterGoal = new TrapezoidProfile.State(200, 0);
    TrapezoidProfile.State shooterSetpoint;
    TrapezoidProfile shooterProfile = new TrapezoidProfile(new TrapezoidProfile.Constraints(1, 1));
    PositionVoltage shooterPosition;

    public LaunchShooter(Shooter shooter) {
        this.shooter = shooter;
        addRequirements(shooter);
    }

    @Override
    public void initialize() {
        shooter.setLauncherSpeed(0);
        shooter.setFeederSpeed(0);
        shooterSetpoint = new TrapezoidProfile.State();
        shooterPosition = new PositionVoltage(0);
    }

    @Override
    public void execute() {
        shooterSetpoint = shooterProfile.calculate(1d / 50, shooterSetpoint, shooterGoal);
        shooterPosition.Position = shooterSetpoint.position;
        shooterPosition.Velocity = shooterSetpoint.velocity;
        Shooter.launcherMotor.setControl(shooterPosition);
        System.out.println(shooterPosition.Velocity);
        if (shooterPosition.Velocity > 0.5) {
            shooter.setFeederSpeed(0.1);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setSpeed(0);
    }
}

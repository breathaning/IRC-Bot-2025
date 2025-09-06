package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.ShooterConstants.FeederState;
import frc.robot.subsystems.Feeder;

public class SetFeederState extends Command {
    private Feeder feeder;
    private FeederState feederState;

    public SetFeederState(Feeder feeder, FeederState feederState) {
        this.feeder = feeder;
        this.feederState = feederState;
        addRequirements(feeder);
    }

    @Override
    public void initialize() {
        feeder.setSpeed(feederState.motorSpeed);
    }

    @Override
    public void end(boolean i) {
        feeder.setSpeed(FeederState.IDLE.motorSpeed);
    }
}

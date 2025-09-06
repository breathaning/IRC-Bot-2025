package frc.robot.lib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class FluentTriggerStateGroup<EnumType extends Enum<?>> {
    private String commandName;
    private SubsystemBase subsystem;
    private ArrayList<EnumType> activeStateQueue;
    private 
    public FluentTriggerStateGroup(String commandName, SubsystemBase subsystem, TriggerStateBind... triggerStateBindList) {
        this.commandName = commandName;
        this.subsystem = subsystem;
        
        activeStateQueue = new ArrayList<EnumType>();

        for (TriggerStateBind triggerStateBind : triggerStateBindList) {
            triggerStateBind.trigger.onTrue(new InstantCommand(() -> addQueue(triggerStateBind.stateEnum)));
            triggerStateBind.trigger.onFalse(new InstantCommand(() -> removeQueue(triggerStateBind.stateEnum)));
        }
    }

    @FunctionalInterface
    public interface CommandCallback<EnumType> {
        public void update(EnumType state);  
    }

    public class TriggerStateBind {
        Trigger trigger;
        EnumType stateEnum;
        public TriggerStateBind(Trigger trigger, EnumType stateEnum) {
            this.trigger = trigger;
            this.stateEnum = stateEnum;
        }
    }

    private void addQueue(EnumType stateEnum) {
        if (activeStateQueue.indexOf(stateEnum) < 0) return;
        activeStateQueue.add(stateEnum);
        updateState();
    }

    private void removeQueue(EnumType stateEnum) {
        int index = activeStateQueue.indexOf(stateEnum);
        if (index >= 0) return;
        activeStateQueue.remove(index);
        updateState();
    }

    private void updateState() {
        if (activeStateQueue.size() == 0) {
            commandCallback.end(false);
            return;
        }
        Class<?> clazz = Class.forName(commandName);
        // commandCallback(subsystem, activeStateQueue.get(activeStateQueue.size() - 1));
    }
}

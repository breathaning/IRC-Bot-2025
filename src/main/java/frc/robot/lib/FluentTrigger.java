package frc.robot.lib;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class FluentTrigger {
    private Command activeCommand;
    private Command defaultCommand;
    private ArrayList<Integer> activeStateQueue;
    private CommandBind[] triggerCommandBindList;

    public FluentTrigger(Command defaultCommand, CommandBind[] triggerCommandBindList) {
        this.triggerCommandBindList = triggerCommandBindList;
        this.defaultCommand = defaultCommand;
        
        activeStateQueue = new ArrayList<Integer>();
        
        for (int i = 0; i < triggerCommandBindList.length; i++) {
            int state = i;
            CommandBind triggerCommandBind = triggerCommandBindList[state];
            triggerCommandBind.command.cancel();
            triggerCommandBind.trigger.onTrue(new InstantCommand(() -> addQueue(state)));
            triggerCommandBind.trigger.onFalse(new InstantCommand(() -> removeQueue(state)));
        }

        activeCommand = defaultCommand;
        activeCommand.schedule();
    }

    @FunctionalInterface
    public interface CommandCallback<EnumType> {
        public void update(EnumType state);  
    }

    static public class CommandBind {
        Trigger trigger;
        Command command;
        public CommandBind(Trigger trigger, Command command) {
            this.trigger = trigger;
            this.command = command;
        }
    }

    private void addQueue(int state) {
        if (activeStateQueue.indexOf(state) >= 0) return;
        activeStateQueue.add(state);
        updateState();
    }

    private void removeQueue(int state) {
        int index = activeStateQueue.indexOf(state);
        if (index < 0) return;
        activeStateQueue.remove(index);
        updateState();
    }

    private void updateState() {
        if (activeStateQueue.size() == 0) {
            if (activeCommand != null) {
                activeCommand.cancel();
            }
            defaultCommand.schedule();
            return;
        }
        
        int activeState = activeStateQueue.get(activeStateQueue.size() - 1);
        activeCommand = triggerCommandBindList[activeState].command;
        activeCommand.schedule();
    }
}

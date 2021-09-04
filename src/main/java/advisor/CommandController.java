package advisor;

import advisor.interfaces.Command;
import advisor.interfaces.CommandWithArgs;

public class CommandController {

    Command command;

    CommandWithArgs commandWithArgs;
    String argument;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }

    public void setCommandWithArgs(CommandWithArgs commandWithArgs) {
        this.commandWithArgs = commandWithArgs;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }

    public void executeCommandWithArgs() {
        commandWithArgs.execute(argument);
    }


}

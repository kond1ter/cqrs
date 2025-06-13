package command.handler;

import command.command.Command;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}

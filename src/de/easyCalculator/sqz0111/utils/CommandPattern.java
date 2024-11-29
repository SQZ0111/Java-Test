package de.easyCalculator.sqz0111.utils;

import java.util.regex.Pattern;

public class CommandPattern {
    private Pattern pattern;
    private Command command;

    public CommandPattern(Command command, Pattern pattern) {
        this.pattern = pattern;
        this.command = command;
    }
    public Command getCommand() {
        return this.command;
    }
    public Pattern getPattern() {
        return this.pattern;
    }
}

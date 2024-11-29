package de.easyCalculator.sqz0111.utils;

import de.easyCalculator.sqz0111.MathFunctions.Addition;
import de.easyCalculator.sqz0111.MathFunctions.Division;
import de.easyCalculator.sqz0111.MathFunctions.Multiplication;
import de.easyCalculator.sqz0111.MathFunctions.Subtraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.HashMap;

public class Executor {
    private HashMap commands;
    private String formattedInput;
    private static final Pattern ADD_PATTERN = Pattern.compile("(add|addition|addieren|plus|\\+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SUBTRACT_PATTERN = Pattern.compile("(sub|subtract|subtrahieren|minus|-)", Pattern.CASE_INSENSITIVE);
    private static final Pattern MULTIPLY_PATTERN = Pattern.compile("(mul|multiply|multiplizieren|mal|\\*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DIVIDE_PATTERN = Pattern.compile("(div|divide|dividieren|teilen|/)", Pattern.CASE_INSENSITIVE);
    private ArrayList<CommandPattern> allPatterns = new ArrayList<CommandPattern>();

    public Executor() {
        commands.put("add",new Addition());
        commands.put("subtract",new Subtraction());
        commands.put("multiply",new Multiplication());
        commands.put("divide",new Division());
        allPatterns.add(new CommandPattern(new Addition(),ADD_PATTERN));
        allPatterns.add(new CommandPattern(new Subtraction(),SUBTRACT_PATTERN));
        allPatterns.add(new CommandPattern(new Multiplication(),MULTIPLY_PATTERN));
        allPatterns.add(new CommandPattern(new Division(),DIVIDE_PATTERN));
    }
    private void executeCommand(String userInput) {
        //iterate over patterns to look if userInput matches ones of those
        //execute command according to which pattern was matched
        //make own commandPatttern class
        for(CommandPattern pattern: allPatterns) {
            Matcher matcher = pattern.getPattern().matcher(userInput.toLowerCase(Locale.ROOT));
            if(matcher.find()) {
                //execute command
                pattern.getCommand().execute();

            }
        }
    }
}

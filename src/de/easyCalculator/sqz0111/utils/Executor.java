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
    private HashMap<String, Command> commands = new HashMap<>();
    private String formattedInput;
    private static final Pattern ADD_PATTERN = Pattern.compile("(add|addition|addieren|plus|\\+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern SUBTRACT_PATTERN = Pattern.compile("(sub|subtract|subtrahieren|minus|-)", Pattern.CASE_INSENSITIVE);
    private static final Pattern MULTIPLY_PATTERN = Pattern.compile("(mul|multiply|multiplizieren|mal|\\*)", Pattern.CASE_INSENSITIVE);
    private static final Pattern DIVIDE_PATTERN = Pattern.compile("(div|divide|dividieren|teilen|/)", Pattern.CASE_INSENSITIVE);
    private ArrayList<CommandPattern> allPatterns = new ArrayList<CommandPattern>();



    public Executor(double firstOperand, double secondOperand, ArrayList<Double> otherOperands) {
        commands.put("add",new Addition(firstOperand, secondOperand, otherOperands));
        commands.put("subtract",new Subtraction(firstOperand, secondOperand, otherOperands));
        commands.put("multiply",new Multiplication(firstOperand, secondOperand, otherOperands));
        commands.put("divide",new Division(firstOperand, secondOperand, otherOperands));
        allPatterns.add(new CommandPattern(new Addition(firstOperand, secondOperand, otherOperands),ADD_PATTERN));
        allPatterns.add(new CommandPattern(new Subtraction(firstOperand, secondOperand, otherOperands),SUBTRACT_PATTERN));
        allPatterns.add(new CommandPattern(new Multiplication(firstOperand, secondOperand, otherOperands),MULTIPLY_PATTERN));
        allPatterns.add(new CommandPattern(new Division(firstOperand, secondOperand, otherOperands),DIVIDE_PATTERN));

    }


    public void executeCommand(String userInput) {
        //iterate over patterns to look if userInput matches ones of those
        //execute command according to which pattern was matched
        //make own commandPatttern class
        for(CommandPattern pattern: allPatterns) {

            Matcher matcher = pattern.getPattern().matcher(userInput.toLowerCase(Locale.ROOT));
            if(matcher.find()) {
                //execute command
                System.out.println(pattern.getCommand());
                pattern.getCommand().executeCalculation(pattern.getCommand());

            }
        }
    }
}

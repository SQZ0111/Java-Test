package de.easyCalculator.sqz0111.utils;

import java.awt.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;


public class MathUserInteraction {
    private final MathStringBuilder introMessage = new MathStringBuilder();
    private ArrayList<Double> operands;
    private String operator;
    private boolean stop = false;
    // Define regex patterns for numbers and commands
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(?i)(stop|exit|quit)$");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("""
    ^(
    \\+|-|\\*|/|
    divide|multiply|subtract|addition|
    mul|div|sub|add|
    teilen|multiplizieren|subtrahieren|addieren
    )$""", Pattern.CASE_INSENSITIVE);


    public MathUserInteraction() {
        operands = new ArrayList<>();

    }


    private String processOperatorInput(Scanner scan) {
        System.out.println("Please Choose the operand of choice!");
        String userInput = scan.nextLine();
        userInput = userInput.trim();
        if (userInput.isEmpty() || !isOperator(userInput)) {
            return "Invalid operator input";
        }
        return userInput;
    }
    public static boolean isOperator(String userInput) {
        Matcher matcher = OPERATOR_PATTERN.matcher(userInput);
        return matcher.matches();
    }



    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        Matcher matcher = NUMBER_PATTERN.matcher(strNum);
        return matcher.matches();
    }

    private void processNumberInput(Scanner scan) {
        System.out.println("Please provide your calculable numbers.");
        System.out.println("Type [stop|exit|quit] to finish input.");

        while (!stop) {
            System.out.print("Enter number or command: ");
            String input = scan.nextLine().trim();

            Matcher commandMatcher = COMMAND_PATTERN.matcher(input);

            if (commandMatcher.matches()) {
                stop = true;

            } else if (isNumeric(input)) {
                operands.add(Double.parseDouble(input));
            } else {
                System.out.println("Invalid input. Please enter a valid number or a command to stop.");
            }
        }

        System.out.println("Operands entered: " + operands);
        System.out.println("Operator chosen: " + operator);
    }


    private void callCalculation(String operator) throws InterruptedException {
        if(operands.size() > 2) {
            List<Double> subList = operands.subList(2, operands.size());
            ArrayList<Double> restOperands = new ArrayList<>(subList);
            Executor calculationExecutor = new Executor(operands.get(0),operands.get(1),restOperands);
            calculationExecutor.executeCommand(operator);
        } else {
            Executor calculationExecutor = new Executor(operands.get(0), operands.get(1),new ArrayList<>());
            calculationExecutor.executeCommand(operator);
        }


    }


    public void run() {
        try (Scanner scan = new Scanner(System.in)) {
            while(true) {
                System.out.println(introMessage.toString());


                operator = processOperatorInput(scan);
                if (!operator.contains("Invalid")) {
                    processNumberInput(scan);
                    callCalculation(operator);
                    break;
                }
            }


        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

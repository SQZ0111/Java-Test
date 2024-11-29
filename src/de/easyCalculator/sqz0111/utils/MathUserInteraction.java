package de.easyCalculator.sqz0111.utils;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class MathUserInteraction {
    private final MathStringBuilder introMessage = new MathStringBuilder();
    private ArrayList<Double> operands;
    private String operator;
    private boolean stop = false;
    // Define regex patterns for numbers and commands
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^-?\\d+(\\.\\d+)?$");
    private static final Pattern COMMAND_PATTERN = Pattern.compile("^(?i)(stop|exit|quit)$");
    private static final Pattern OPERATOR_PATTERN = Pattern.compile("^[+\\-*/]$");


    public MathUserInteraction() {
        operands = new ArrayList<>();

    }


    private String processOperatorInput(Scanner scan) {
        System.out.println("Please Choose the operand of choice!");
        String userInput = scan.nextLine();
        userInput = userInput.trim();
        if (userInput.isEmpty()) {
            throw new Error("Please provide valid operand input");
        }
        return userInput;
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
            Matcher numberMatcher = NUMBER_PATTERN.matcher(input);

            if (commandMatcher.matches()) {
                stop = true;
            } else if (numberMatcher.matches()) {
                operands.add(Double.parseDouble(input));
            } else {
                System.out.println("Invalid input. Please enter a valid number or a command to stop.");
            }
        }

        System.out.println("Operands entered: " + operands);
        System.out.println("Operator chosen: " + operator);
    }


    public void run() {
        try (Scanner scan = new Scanner(System.in)) {

            System.out.println(introMessage.toString());


            operator = processOperatorInput(scan);

            processNumberInput(scan);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

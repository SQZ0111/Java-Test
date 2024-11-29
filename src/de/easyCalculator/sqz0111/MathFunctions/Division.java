package de.easyCalculator.sqz0111.MathFunctions;

import de.easyCalculator.sqz0111.utils.Command;

import java.util.ArrayList;
import java.util.Arrays;

public class Division implements Command {
    static double DEFAULT_VALUE = 0.0;
    private double firstOperand = DEFAULT_VALUE;
    private double secondOperand = DEFAULT_VALUE;
    private ArrayList<Double> otherOperands;

    public Division(double firstOperand, double secondOperand, ArrayList<Double> otherOperands) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.otherOperands = otherOperands;

    }
    @Override
    public void executeCalculation(Object calculation) {
        if(secondOperand == 0 || otherOperands.contains(0)) {
            throw new NullPointerException("No zero division!");
        }

        double result = firstOperand / otherOperands.stream().reduce(secondOperand, (acc, divisor) -> acc * divisor);
        printResult(result);
    }
}

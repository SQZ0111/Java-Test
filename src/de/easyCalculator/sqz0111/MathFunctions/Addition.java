package de.easyCalculator.sqz0111.MathFunctions;

import de.easyCalculator.sqz0111.utils.Command;

import java.util.ArrayList;

public class Addition implements Command {
    static double DEFAULT_VALUE = 0.0;
    private double firstOperand = DEFAULT_VALUE;
    private double secondOperand = DEFAULT_VALUE;
    private double[] otherOperands;
    public Addition(double firstOperand, double secondOperand, double ...otherOperands) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.otherOperands = otherOperands;

    }
    @Override
    public double executeCalculation(Object calculation) {
        return 0.0;
    }
}

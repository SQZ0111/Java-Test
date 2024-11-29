package de.easyCalculator.sqz0111.utils;

public interface Command {
    public void executeCalculation(Object calculation);
    public default void printResult(double result) {
        System.out.println("The Result equals: " + result);
    }
}

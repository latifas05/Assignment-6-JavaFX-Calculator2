package com.example.myapp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class HelloController {
    private double operand1;
    private double operand2;
    private char operator;
    private boolean error = false;  // To track errors
    private StringBuilder input = new StringBuilder();  // To collect user input
    private StringBuilder expression = new StringBuilder(); // To hold the full expression

    @FXML
    private TextField display;

    // Method to handle digit button presses
    public void handleDigit(javafx.event.ActionEvent event) {
        String digit = ((Button) event.getSource()).getText();  // Get digit from button
        input.append(digit);  // Append digit to input
        expression.append(digit); // Append digit to expression
        display.setText(expression.toString());  // Update display
    }

    // Method to handle operator button presses (+, -, *, /)
    public void handleOperator(javafx.event.ActionEvent event) {
        String operatorStr = ((Button) event.getSource()).getText();  // Get operator from button
        if (input.length() > 0) {  // Check if there's an input before applying operator
            operand1 = Double.parseDouble(input.toString());  // Set first operand
            expression.append(" ").append(operatorStr).append(" "); // Update expression with operator
            input.setLength(0);  // Clear input for next number
            operator = operatorStr.charAt(0);  // Set operator
            display.setText(expression.toString()); // Update display with expression
        }
    }

    // Method to handle the equals button (=)
    public void handleEquals(javafx.event.ActionEvent event) {
        if (input.length() > 0) {  // Ensure input is not empty
            operand2 = Double.parseDouble(input.toString());  // Set second operand
            double result = getResult();  // Perform calculation

            // Handle errors, such as division by zero
            if (isError()) {
                display.setText("Error");  // Show error message
            } else {
                // Show full calculation in the format "operand1 operator operand2 = result"
                display.setText(expression.toString() + " = " + result);
            }
            input.setLength(0);  // Clear input for new entries
            expression.setLength(0); // Clear expression after displaying result
        }
    }

    // Method to handle the clear button (C)
    public void handleClear(javafx.event.ActionEvent event) {
        reset();  // Reset calculator
        input.setLength(0);  // Clear input
        expression.setLength(0); // Clear expression
        display.clear();  // Clear display
    }

    // Method to handle the comma button (,) for decimal input
    public void handleComma(javafx.event.ActionEvent event) {
        if (input.indexOf(".") == -1) {  // Check if decimal point is not already present
            input.append(".");  // Append decimal point
            expression.append("."); // Append to expression
            display.setText(expression.toString());  // Update display
        }
    }

    // Method to handle percentage button press
    public void handlePercentage(javafx.event.ActionEvent event) {
        if (input.length() > 0) {
            operand1 = Double.parseDouble(input.toString());  // Get the current input
            double percentageResult = operand1 / 100;  // Calculate percentage
            display.setText(Double.toString(percentageResult));  // Show result
            input.setLength(0);  // Clear input for new entries
            expression.setLength(0); // Clear expression
        }
    }

    // Method to handle the +/- button for negative numbers
    public void handleToggleSign(javafx.event.ActionEvent event) {
        if (input.length() > 0) {  // Ensure input is not empty
            double currentValue = Double.parseDouble(input.toString());  // Get current value
            currentValue = -currentValue;  // Toggle sign
            input.setLength(0);  // Clear input
            input.append(currentValue);  // Set toggled value as input
            display.setText(input.toString());  // Update display
        }
    }

    // Method to get the calculation result
    private double getResult() {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    error = true;  // Set error on division by zero
                    return 0;
                }
            default:
                error = true;  // Set error for invalid operator
                return 0;
        }
    }

    public boolean isError() {
        return error;  // Return error status
    }

    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = '\0';
        error = false;
    }
}

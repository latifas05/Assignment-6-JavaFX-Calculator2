package com.example.myapp;

public class Calculator {

    private double operand1;
    private double operand2;
    private char operator;
    private boolean error = false;

    public void setOperand1(double operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(double operand2) {
        this.operand2 = operand2;
    }

    public void setOperator(char operator) {
        this.operator = operator;
    }

    public boolean isError() {
        return error;
    }

    public double getResult() {
        // Логика вычисления результата
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
                    error = true;
                    return 0;  // Ошибка: деление на ноль
                }
            default:
                error = true;
                return 0;
        }
    }

    public void reset() {
        operand1 = 0;
        operand2 = 0;
        operator = '\0';
        error = false;
    }
}

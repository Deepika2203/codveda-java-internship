import java.util.Scanner;

public class Calculator {

    // Method for addition
    static double add(double a, double b) {
        return a + b;
    }

    // Method for subtraction
    static double subtract(double a, double b) {
        return a - b;
    }

    // Method for multiplication
    static double multiply(double a, double b) {
        return a * b;
    }

    // Method for division
    static double divide(double a, double b) {
        if (b == 0) {
            System.out.println("Error: Division by zero is not allowed.");
            return 0;
        }
        return a / b;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double num1 = sc.nextDouble();

        System.out.print("Enter second number: ");
        double num2 = sc.nextDouble();

        System.out.print("Choose operation (+ - * /): ");
        char operation = sc.next().charAt(0);

        double result;

        switch (operation) {
            case '+':
                result = add(num1, num2);
                break;

            case '-':
                result = subtract(num1, num2);
                break;

            case '*':
                result = multiply(num1, num2);
                break;

            case '/':
                result = divide(num1, num2);
                break;

            default:
                System.out.println("Invalid operation selected.");
                return;
        }

        System.out.println("Result: " + result);
        sc.close();
    }
}
 

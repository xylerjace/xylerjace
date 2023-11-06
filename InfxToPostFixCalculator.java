import java.util.Stack;
import java.util.Scanner;

public class InfxToPostFixCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String postfix  = "";
        System.out.print("Enter an expression: ");
        String expression = scanner.nextLine();
        
        Stack<Character>stk= new Stack<>(); 
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue; // Ignore spaces
            } else if (Character.isDigit(ch) || ch == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i));
                    postfix += ch;
                    i++;
                }
                i--;
                numbers.push(Double.parseDouble(num.toString()));
            } else if (ch == '(') {
                operators.push(ch);
                stk.push(expression.charAt(i));
            } else if (ch == ')') {
                while (operators.peek() != '(') {
                    applyOperator(numbers, operators);
                }
                 while(!stk.isEmpty() && stk.peek()!='('){          // keep popping till opening bracket is found
                    postfix +=stk.pop();
                }               
                if(!stk.isEmpty()){
                    stk.pop();
                }
               operators.pop(); // Pop the '('
            }
                else if (isOperator(ch)) {
                   while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                       applyOperator(numbers, operators);
                       postfix += stk.pop();
                   }
                   operators.push(ch);
                   stk.push(ch);
               }
        }

        while (!operators.isEmpty()) {
            applyOperator(numbers, operators);
        }
        while(!stk.isEmpty()){
            postfix+=stk.pop();
        }

        double result = numbers.pop();
        System.out.println("PostFix: "+ postfix);
        System.out.println("Result of the expression: " + result);
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '^') {
            return 3;
        }
        return 0;
    }

    public static void applyOperator(Stack<Double> numbers, Stack<Character> operators) {
        char operator = operators.pop();
        double operand2 = numbers.pop();
        double operand1 = numbers.pop();

        if (operator == '+') {
            numbers.push(operand1 + operand2);
        } else if (operator == '-') {
            numbers.push(operand1 - operand2);
        } else if (operator == '*') {
            numbers.push(operand1 * operand2);
        } else if (operator == '/') {
            numbers.push(operand1 / operand2);
        } else if (operator == '^') {
            numbers.push(Math.pow(operand1, operand2));
        }
    }
}

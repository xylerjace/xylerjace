import java.util.Scanner;
import java.util.*;


public class PemdasCalculator {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		String equation = null;
		
		System.out.print("Enter equation: ");
		equation = scan.nextLine();	
		equation = equation.replaceAll("\\s","");
		scan.close();
		Stack<Double> num = new Stack<Double>();
		Stack<Character>operator =new Stack<Character>();
		for(int i = 0; i<equation.length();i++) {
		
			if(Character.isDigit(equation.charAt(i))) {
				StringBuilder str = new StringBuilder();
				while(i < equation.length() && (Character.isDigit(equation.charAt(i)) || (equation.charAt(i) == '-') && Character.isDigit(equation.charAt(i+1)))){
					str.append(equation.charAt(i));
					i++;
				}
				i--;
				
				double numb = Double.parseDouble(str.toString());
	            num.push(numb);
			}
			else if(Character.isDigit(equation.charAt(i))){
				num.push((double) Character.getNumericValue(equation.charAt(i)));
			}
			
			if(equation.charAt(i) == '(') {
				operator.push(equation.charAt(i));
			}
			else if(equation.charAt(i) == ')') {
				while(operator.peek() != '(') {
					calculate(num,operator);
				}
				operator.pop();
				
			}
			else if(operators(equation.charAt(i))) {
				while(!operator.isEmpty() && (operator.peek() == '*' || operator.peek() == '/') && (equation.charAt(i) == '-' || equation.charAt(i) == '+')) {
					calculate(num,operator);
				}
				operator.push(equation.charAt(i));
			}
		}

		while(true) {
			if(num.size() <= 1) {
				System.out.println("The answer is "+ num.peek());
				break;
			}else {
				calculate(num,operator);
					
			}
		}
	}
	
	public static boolean operators(char op) {
		
		if(op =='*' || op == '+' || op== '-' || op == '/') {
			return true;
		}
		else {
			return false;
		}
		
	}
	public static void calculate(Stack<Double>num, Stack<Character>operators) {
		double operand2 = num.pop();
		double operand1 = num.pop();
		char operator = operators.pop();
		num.push(answer(operand1,operand2,operator));
		
	}
	
	public static Double answer(Double operand1, double operand2, char operator) {
		double answer = 0;
		
		switch(operator) {
		
		case '+': answer = operand1 + operand2;break;
		case '-': answer = operand1 - operand2;break;
		case '*': answer = operand1 * operand2;break;
		case '/': answer = operand1 / operand2;break;
		
		}
		return answer;
	}

}

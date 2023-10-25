	import java.util.*;
	public class InfixToPostFix {
	    public static void main(String[] args){
	        Scanner scan = new Scanner(System.in);
	        String equation = null;
	        StringBuilder postfix = new StringBuilder();
	
	        System.out.print("Enter equation: ");
			equation = scan.nextLine();	
			equation = equation.replaceAll("\\s","");
			scan.close();
			//for conversion of infix to postFix
			Stack<Double> operands = new Stack<Double>();
			Stack<Character>ch=new Stack<Character>();
			Stack<Character>operator = new Stack<Character>();
			
	
	        for(int i = 0; i < equation.length(); i++){
	            if(Character.isDigit(equation.charAt(i))){
	                StringBuilder str = new StringBuilder();
	                while(i < equation.length() && (Character.isDigit(equation.charAt(i)))){
						str.append(equation.charAt(i));
						i++;
					}
					i--;
					
					int number = Integer.valueOf(str.toString());
		            operands.push((double) number);
		            postfix.append(number);
		  
	            }
	            else if(equation.charAt(i) == '('){
	              ch.push(equation.charAt(i));
	              operator.push(equation.charAt(i));
	            }
	           else if(equation.charAt(i) == ')'){
	        	   	while(!ch.isEmpty() && ch.peek() != '(') {
	        	   		postfix.append(ch.pop());
	        	   	}
	        	    if (!ch.isEmpty() && ch.peek() == '(') {
	        	        ch.pop(); 
	        	    }
	        	    while(operator.peek() != '(') {
						calculate(operands,operator);
					}
					operator.pop();
	           }	          
	            else if(isOperator(equation.charAt(i))){
	                while(!ch.isEmpty() && (hasHigherPrecedence(ch.peek(), equation.charAt(i)))) { 
	                	postfix.append(ch.pop());
	                }
	                ch.push(equation.charAt(i));
	                if(!operator.isEmpty() && hasHigherPrecedence(operator.peek(), equation.charAt(i))) {
		                while (!operator.isEmpty() && hasHigherPrecedence(operator.peek(), equation.charAt(i))) {
		                    calculate(operands, operator);
		                }
	                }else {
	                while(!operator.isEmpty() && (operator.peek() == '/' || operator.peek() == '*') && (equation.charAt(i) == '/' || equation.charAt(i) == '*')) {
	                	
	                	calculate(operands,operator);
	                }
	                }
	                

					operator.push(equation.charAt(i));
	            }
	            
	            System.out.println(operands);
	            System.out.println(operator);
	            
	        }
	        while(!ch.isEmpty()) {
	        	
	        	postfix.append(ch.pop());
	        }
	        System.out.println("The postfix conversion is : " + postfix);
	        
	        
	        while(true) {
				if(operands.size() <= 1) {
					System.out.println("The answer is "+ operands.peek());
					break;
				}else {
					calculate(operands,operator);
						
				}
			}
	
	    }
	
	    static public boolean isOperator(char ch){
	        if(ch == '+' || ch == '-' || ch == '*' || ch == '/'){
	            return true;
	        }else{
	            return false;
	        }
	    }
	    public static boolean hasHigherPrecedence(char op1, char op2){
	        if((op1 == '*' || op1 == '/') &&  (op2 == '+' || op2 == '-')){
	            return true;
	        }
	        else{
	            return false;
	        }
	    }

	public static void calculate(Stack<Double>num, Stack<Character>operators) {
		double operand2 = num.pop();
		double operand1 = num.pop();
		char operator = operators.pop();
		num.push(answer(operand1,operand2,operator));
		
	}
	
	public static double answer(double operand1, double operand2, char operator) {
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
	
	

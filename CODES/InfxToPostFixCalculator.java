import java.util.*;

public class InfxToPostFixCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String postfix = "";
        System.out.print("Enter an expression: ");
        String expression = scanner.nextLine();

        Stack<Character> stk = new Stack<>();
        Stack<Double> numbers = new Stack<>(); // Use Double for numbers in postfix expression

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);

            if (ch == ' ') {
                continue; // Ignore spaces
            } else if (Character.isDigit(ch) || ch == '.') {
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    postfix += ch;
                    i++;
                }
                i--;
            } else if (ch == '(') {
                stk.push(expression.charAt(i));
            } else if (ch == ')') {
                while (!stk.isEmpty() && stk.peek() != '(') {
                    postfix += stk.pop();
                }
                if (!stk.isEmpty()) {
                    stk.pop();
                }
            } else if (isOperator(ch)) {
                while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(ch)) {
                    postfix += stk.pop();
                }
                stk.push(ch);
            }
        }
        while (!stk.isEmpty()) {
            postfix += stk.pop();
        }

        System.out.println("PostFix: " + postfix);
        
       ArrayList<Integer>ans = new ArrayList<>();
       for(int i = 0;i<postfix.length();i++){
                if (Character.isDigit(postfix.charAt(i))) {
                ans.add(Character.getNumericValue(postfix.charAt(i)));
               }else if(isOperator(postfix.charAt(i))){
                if (ans.size() < 2) {
                    System.out.println("Invalid expression.");
                    return;
                }
                
                      int operand1 = ans.remove(ans.size()-2);
                      int operand2 = ans.remove(ans.size()-1);
                      int result = calculate(operand1,operand2,postfix.charAt(i));
                      ans.add(result);
                      
                      for(int j=0; j< ans.size();j++){   
                           System.out.print(ans.get(j)); 
                        }
                        for(int k = i+1; k< postfix.length();k++){
                              System.out.print(postfix.charAt(k));
                        }
                      System.out.println();
                      
               }
              // System.out.print(ans.get(0));
                     

       }
 }
   public static int calculate(int operand1,int operand2,char opr){
            int result = 0;
            switch(opr){
               case '+': result = operand1 + operand2;break;
               case '-': result = operand1 - operand2;break;
               case '*': result = operand1 * operand2;break;
               case '/': result = operand1 / operand2;break;
            }
            return result;
            
   }
    public static void printSolution(){
         
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
      

   }


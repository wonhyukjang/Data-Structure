/*  Test program for CS310, SP 15, program #3
    Each expression is worth 10 points
    Alan Riggins
*/    

import data_structures.*;

public class ExpressionEvaluatorGrader {
    ExpressionEvaluator calculator; 
    
    public ExpressionEvaluatorGrader() {
        calculator = new ExpressionEvaluator();
        runTests();
        }
        
    private void runTests() {
        String expression, answer;
        
        expression = "( ( 2 + 3";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                " returns " + answer);
                
        expression = "2 + 3 )";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but the expression " + expression +
                " returns " + answer);
                
        expression = "2 ( 2 + 3 )";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but  the expression " + expression +
                " returns " + answer); 
                
        expression = "2 * ( 2 + 3 ) )";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but  the expression " + expression +
                " returns " + answer);                                
                
        expression = "2 * ( 4 + 2 ) - 11";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("1.0"))
            System.out.println("**** ERROR, expecting 1.0, but  the expression " + expression +
                " returns " + answer); 
                
        expression = "2 ^ 3 * 2";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("16.0"))
            System.out.println("**** ERROR, expecting 16.0, but  the expression " + expression +
                " returns " + answer); 
                
        expression = "2 + 3 * 2";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("8.0"))
            System.out.println("**** ERROR, expecting 8.0, but  the expression " + expression +
                " returns " + answer); 
                
        expression = "2 ^ 3 * 4 + 5";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("37.0"))
            System.out.println("**** ERROR, expecting 37.0, but  the expression " + expression +
                " returns " + answer); 
                
        expression = "( 2 * ( 12 + 13 - 4 * 5 + 2 ) / 7 ) ^ 2";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("4.0"))
            System.out.println("**** ERROR, expecting 4.0, but  the expression " + expression +
                " returns " + answer);                
                
        expression = "4.2 - 2.2";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("2.0"))
            System.out.println("**** ERROR, expecting 2.0, but  the expression " + expression +
                " returns " + answer);
                
        expression = "2 ^ ) 3 * 2 (";       
        answer = calculator.processInput(expression);
        if(!answer.trim().toUpperCase().equals("ERROR"))
            System.out.println("**** ERROR, expecting ERROR, but  the expression " + expression +
                " returns " + answer); 
                
        System.out.println("Done");                                                                                                          
        }
        
    public static void main(String [] args) {
        new ExpressionEvaluatorGrader();
        }
    }
    
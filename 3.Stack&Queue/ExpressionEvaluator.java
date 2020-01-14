/*  Wonhyuk Jang
    masc0812
*/
import data_structures.*;

import java.util.StringTokenizer;
public class ExpressionEvaluator {
	StringTokenizer t;
	String token;
	Stack<String> st = new Stack<String>(); //Create Stack
	Queue<String> qu = new Queue<String>(); //Create Queue

	private static boolean isOperator(String token){ //Check token is Operator
		if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^"))
			return true;
		return false;
	}
	private static boolean isNumber(String token){ //Check token is Number
		try { 
			double x = Double.parseDouble(token);
				return true;			
		}
		catch(Exception e){
			return false;
		}
	}
	private int precedence(String token) { //Check the precedence of the Operator
		if(token.equals("^"))
			return 3;
		if(token.equals("*") || token.equals("/"))
			return 2;
		if(token.equals("+") || token.equals("-"))
			return 1;
		else
			throw new RuntimeException();
	}
	public String processInput(String s){
		try{
		convertInfix_to_Postfix(s);
		return evaluateExpression();			
		}
		catch(Exception e){
			//System.out.println("Error");
		    return "ERROR";
		}
	}
	private void convertInfix_to_Postfix(String infix){
		t = new StringTokenizer(infix);
		st.makeEmpty();
		qu.makeEmpty();
		while(t.hasMoreTokens()){
			token = t.nextToken();
	    if ( token.equals("(")){ //push the "(" on the stack
	        st.push("(");
	    }
	    if ( token.equals(")")){
	    	while(true){	        	
	    		if(!st.isEmpty())
	    			token = st.pop();
	    		else{
	    			st.push(token);
	    			break;
	    		}
	    		
	    		if(!token.equals("("))
	    			qu.enqueue(token);
	    		else
	    			break;	    		
	        }      
	    }
	    //        pop an operator off the stack and enqueue it in the queue
	    if (isOperator(token)) {
	        while(!st.isEmpty() && !st.peek().equals("(") && precedence(st.peek()) >= precedence(token)) {     			
	        	qu.enqueue(st.pop());
	        }
	        st.push(token); 
	    }
	    if (isNumber(token)) //push number token on the queue
	        qu.enqueue(token);
	    else
	    	if(!isOperator(token) && !token.equals(")") && !token.equals("(")) //Catch illegal number call on isNumber method such as 1.2.1 and Character
		    	throw new RuntimeException();
	     // end for each token do
		}
	while (!st.isEmpty()){
			qu.enqueue(st.pop());		
	}
     //System.out.println(qu);

	}
	private String evaluateExpression(){	
		double result = 0.0;
	while (!qu.isEmpty() ) { //If queue is not empty, dequeue it until it's empty
		token = qu.dequeue();
		//System.out.println(token);
		 if(token.equals("(")) //Catch the left parenthesis error
	        	return "ERROR";
		 if(token.equals(")")) //Catch the right parenthesis error
			 	return "ERROR";
	     if (isNumber(token))
	        st.push(token);
	     else if(isOperator(token)) { //Calculate the result
	    	 if(st.isEmpty() && st.pop().isEmpty()){
	    		 throw new RuntimeException();
	    	 }
	        double d2 = Double.parseDouble(st.pop()); //Convert number token to
	        double d1 = Double.parseDouble(st.pop()); //the double
	        if(token.equals("+"))
	        	result = d1 + d2;
	        if(token.equals("-"))
	        	result = d1 - d2;
	        if(token.equals("*"))
	        	result = d1 * d2;
	        if(token.equals("/")){
	        	if(d2 == 0) //Catch the error when number is divided by 0
	        		return "ERROR";
	        	result = d1/ d2;
	        }
	        if(token.equals("^"))
	        	result = (Math.pow(d1,d2));
	       String answer = result + " "; //convert answer to the String
	       st.push(answer);
	        }
		}
		if(st.size() > 1){ //Catch the error when stack is not empty before starting 
						   //new calculation
			st.makeEmpty();
			return "ERROR";
		}
	   return st.pop();
}
}
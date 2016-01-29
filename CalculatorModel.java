package calc;

/**
 * Performs calculations and communicated with Controller for displaying answers
 * 
 * @author Daniel Spagnuolo
 * @version 1
 * @since 1.8_60
 * 
 * 
 */
public class CalculatorModel {	
	/**For holding the error state*/
	private boolean errorState = false;
	
	/**Float to hold the result of the calculations*/
	private float calculations=0;
	
	/**float for holding the first number in the calculation*/
	private float num1=0;
	
	/**float for holding the second number in the calculation*/
	private float num2=0;
	
	/**char for holding the operator in the calculation*/
	private char operator;


	
	/**
	 * divides the operand string into 3 variable, num1, operator, num2.
	 * gives it a handler. Also check for errors with decimals.
	 * @param operands [String, contain the operands inputted in calcview]
	 * @return [void]
	 */
	void setStringOperands(String operands){
		int operandIndex = 0; //keep track of the index of the operator
		if (operands.charAt(0)=='/' || operands.charAt(0)=='*'){
			errorState = true;
			return;
		}
		
		//loop to check for incorrect decimals.
		for (int i=0; i < operands.length(); i++){
			if (operands.charAt(i)=='.'){ //if decimal is found
				if (i ==0){ //if the decimal is at the beginning, this is an error.
					errorState = true;
					return;
				}
				//if not at the end of operand and a decimal is found after or before another decimal, error.
				if (i < operands.length() && (operands.charAt(i+1)=='.' ||  operands.charAt(i-1)=='.')){ 
					errorState = true;
					return;
				}
				//if a digit is not infront of the decimal, this is an error.
				if (!Character.isDigit(operands.charAt(i-1))){
					errorState = true;
					return;
				}
			}
		}
		
		//for every char in equation, except for the first char incase it is a negative,
		//look for the first occurrence of an operand
		for (int i = 1; i < operands.length(); i++){
			if (Character.toString(operands.charAt(i)).matches("[-+*/]")){
				operandIndex = i; //save location of operator for substringing
				operator = operands.charAt(i); //save operator char
				//if operand is not last char and is '*', check for '/' as next char
				if (i < operands.length()-1 && operands.charAt(i)=='*' && operands.charAt(i+1)=='/'){ 
					//2*/ detected
					operator = '/'; //calculation can be written as num1 / num1
					num1 = Float.parseFloat(operands.substring(0, operandIndex));
					num2 = Float.parseFloat(operands.substring(0, operandIndex));
					return;
				}
				//if operand is not last char and is '*', check for '/' as next char
				else if (i < operands.length()-1 && operands.charAt(i)=='/' && operands.charAt(i+1)=='*'){ 
					//2/* detected
					operator = '*'; //calculation can be written as num1 * num1
					num1 = Float.parseFloat(operands.substring(0, operandIndex));
					num2 = Float.parseFloat(operands.substring(0, operandIndex));
					return;
				}
				break;
			}
		}
		//use a substring to capture number before operator as num1
		num1 = Float.parseFloat(operands.substring(0, operandIndex));
		
		//use a substring to capture number after operator as num2
		num2 = Float.parseFloat(operands.substring(operandIndex+1, operands.length()));
	}

	/**
	 * using the num1, operand, and num2 variables, this function 
	 * returns a calculated float. The float is formatted in the CalcView class
	 * @return [Float containing the calculated answer]
	 */
	float getResult(){
			//perform the proper calculation based on the operator value
		
			switch (operator){
			case '+':
				calculations = (num1+num2); //addition
				break;
			case '-':
				calculations = (num1-num2); //subtraction
				break;
			case '/':
				if (num2 ==0){ //check for divide by zero
					errorState = true; //set error state to true and dont perform a calculation.
				} else{
					calculations = (num1/num2); //division
				}
				break;
			case '*':
				calculations = (num1*num2); //multiplication
				break;
			}
			//return calculaton
		return calculations;
	}
	
	/**
	 * Creates a button reference using the passed in parameters and
	 * gives it a handler.
	 * @return [Boolean, errorstate boolean for determining if an error occurred]
	 */
	boolean getErrorState(){
		return errorState;
	}
	
	/**
	 * Creates a button reference using the passed in parameters and
	 * gives it a handler.
	 * @param eState [boolean, sets the errorstate]
	 * @return [void]
	 */
	void setErrorState(boolean eState){
		errorState = eState;
	}
	
	

}

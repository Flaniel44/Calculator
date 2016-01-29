/*
File name: CalculatorView.java
Author: Danie Spagnuolo, 040 768 219
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1 Part 1
Date: October 7th, 2015
Professor: Svillen Ranev
Purpose: Build a relatively simple GUI for a Calculator Application.
Class list: CalculatorView, Controller
*/
package calc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Sets up the display for the calculator. Also implements an actionlistener for controlling
 * button clicks.
 * 
 * @author Daniel Spagnuolo
 * @version 1
 * @see javax.swing.JPanel
 * @since 1.8_60
 * 
 */
public class CalculatorView extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**{@value #ERROR_DISPLAY_WIDTH} used for the width of the error display label */
	private static final int ERROR_DISPLAY_WIDTH = 25;
	
	/**{@value #ERROR_DISPLAY_HEIGHT} used for the height of the error display label */
	private static final int ERROR_DISPLAY_HEIGHT = 25;
	
	/**{@value #BUTTON_FONT_SIZE} used for the font size of the buttons */
	private static final float BUTTON_FONT_SIZE = 20.0f;
	
	/**{@value #TEXT_FIELD_WIDTH} used for the width of the text field */
	private static final int TEXT_FIELD_WIDTH = 180;
	
	/**{@value #TEXT_FIELD_HEIGHT} used for the height of the text field */
	private static final int TEXT_FIELD_HEIGHT = 30;
	
	/**the calculator display field reference */
	private JTextField display;
	
	/**the error display label reference */
	private JLabel error;
	
	/**the decimal point (dot) button reference */
	private JButton dotButton;
	
	public CalculatorView(){
		// Controller object for use as an actionListener handler
		Controller control = new Controller();
		
		
		//Create and set the necessary fields for an error label
		error = new JLabel("F",SwingConstants.CENTER);
		error.setMinimumSize(new Dimension(ERROR_DISPLAY_WIDTH, ERROR_DISPLAY_HEIGHT));
		error.setPreferredSize(new Dimension(ERROR_DISPLAY_WIDTH, ERROR_DISPLAY_HEIGHT));
		error.setOpaque(true);
		error.setBackground(Color.YELLOW);
		error.setForeground(Color.BLACK);
		
		//Create and set the necessary fields for the calculator display
		display = new JTextField();
		display.setEditable(false);
		display.setPreferredSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
		display.setMinimumSize(new Dimension(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT));
		display.setHorizontalAlignment(SwingConstants.RIGHT);
		display.setText("0.0");
		display.setBackground(Color.WHITE);
		
		//Create checkbox "int"
		JCheckBox checkMode = new JCheckBox("Int");
		checkMode.setOpaque(true);
		checkMode.setBackground(Color.GREEN);
		checkMode.setActionCommand("Int");
		checkMode.addActionListener(control);
		
		//Create radio buttons and add to a button group to prevent
		//multiple buttons from being selected
		JRadioButton tenths = new JRadioButton(".0");
		tenths.setBackground(Color.YELLOW);
		tenths.setActionCommand("Tenths");
		tenths.addActionListener(control);
		JRadioButton hundredths = new JRadioButton(".00");
		hundredths.setSelected(true);
		hundredths.setBackground(Color.YELLOW);
		hundredths.setActionCommand("Hundredths");
		hundredths.addActionListener(control);
		JRadioButton sci = new JRadioButton("Sci");
		sci.setBackground(Color.YELLOW);
		sci.setActionCommand("Sci");
		sci.addActionListener(control);
		
		//Add radio buttons to button group
		ButtonGroup radioGroup = new ButtonGroup();
		radioGroup.add(tenths);
		radioGroup.add(hundredths);
		radioGroup.add(sci);
		
		//Create backspace button and set options.
		JButton backspace = createButton("\u2190", "Backspace", Color.RED, Color.RED, control);
		backspace.setOpaque(false);
		backspace.setBorder(new LineBorder(Color.RED, 1));
		backspace.setPreferredSize(new Dimension(ERROR_DISPLAY_WIDTH, ERROR_DISPLAY_HEIGHT));
		backspace.setFocusable(false);
		backspace.setToolTipText("Backspace (Alt-B)");
		//Allow for the ALT+B combo to work as a backspace shortcut
		backspace.setMnemonic(KeyEvent.VK_B);
		backspace.setContentAreaFilled(false);
		
		//Create a panel to contain the error button, screen, and backspace button
		JPanel screenContents = new JPanel();
		screenContents.setLayout(new FlowLayout());
		
		//create a borderlayout panel to hold error/screen/backspace panel so there is left and right whitespace
		JPanel screenBar = new JPanel();
		screenBar.setLayout(new BorderLayout());
		
		//Adding componensts to the screen panel and setting options
		screenContents.add(error);
		screenContents.add(display);
		screenContents.add(backspace);
		screenContents.setBackground(Color.WHITE);
		
		//adding panel to the border layout center and set options
		screenBar.add(screenContents, BorderLayout.CENTER);
		screenBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50)); //prevents white border around screen from being too large
		
		//Create a panel to hold the radio buttons
		JPanel radioCheck = new JPanel();
		radioCheck.setLayout(new FlowLayout(FlowLayout.CENTER,0,5));
		
		//create a borderlayout panel to hold radio buttons panel so there is left and right whitespace
		JPanel radioCheckBoxBar = new JPanel();
		radioCheckBoxBar.setLayout(new BorderLayout());
		
		//adding checkbox and radio buttons to the panel, with a strut inbetween
		radioCheck.add(checkMode);
		radioCheck.add(Box.createHorizontalStrut(20));
		radioCheck.add(tenths);
		radioCheck.add(hundredths);
		radioCheck.add(sci);
		radioCheck.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		//add panel to borderlayout center and set options
		radioCheckBoxBar.add(radioCheck, BorderLayout.CENTER);
		radioCheck.setBackground(Color.BLACK);
		radioCheckBoxBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		
		//create a grid layout to hold number buttons. 4 columns with 4 rows, hgap and vgap of 5
		JPanel numPad = new JPanel();
		numPad.setLayout(new GridLayout(4,4,5,5));
		
		//Create borderlayout for all the buttons
		JPanel buttons = new JPanel();
		buttons.setLayout(new BorderLayout());
		
		//Array of button text
		String[] btns = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "\u00B1", "0", ".", "+"};
		
		//For every button
		for (int i=0; i < btns.length; i++){
			//create Color variable to hold foreground colour for buttons
			Color color;
			
			//Every 4th button is yellow, the rest are blue
			if ((i+1)%4 == 0)
				color = Color.YELLOW;
			else 
				color = Color.BLACK;
			
			//Call createButton function and add returned JButton to the gridlayout
			//If it's a decimal button, assign to it's field member.
			if (btns[i]=="."){
				dotButton = createButton(btns[i], "Decimal", color, Color.BLUE, control);
				numPad.add(dotButton);
			}else{
				numPad.add(createButton(btns[i], btns[i], color, Color.BLUE, control));
			}	
		}
		// Add small buttons container to the center of the border layount,
		//adding Clear and equals to the left and right of the layout.
		buttons.add(numPad, BorderLayout.CENTER);
		numPad.setBackground(Color.WHITE);
		buttons.add(createButton("C", "Clear", Color.BLACK, Color.RED, control), BorderLayout.WEST);
		JButton equals = createButton("=", "Equals", Color.black, Color.YELLOW, control);
		equals.setMnemonic(KeyEvent.VK_ENTER);
		buttons.add(equals, BorderLayout.EAST);
		
		//Creating Hbox to hold all containers vertically stacked 
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(5,5,5,5));
		setBackground(Color.BLACK);
		
		//add containers
		add(screenBar);
		add(radioCheckBoxBar);
		add(buttons);
	}
	
	/**
	 * Creates a button reference using the passed in parameters and
	 * gives it a handler.
	 * @param text [String, assigned as button label]
	 * @param ac [String, assigned as button action command]
	 * @param fg [Color, assigned as button foreground color]
	 * @param bg [Color, assigned as button background color]
	 * @param handler [ActionListener, handler for action listener]
	 * @return [JButton, returns the newly created JButton reference]
	 */
	private JButton createButton (String text, String ac, Color fg, Color bg, ActionListener handler){
		// local button variable being returned, initialized with button text
		JButton button = new JButton(text);
		
		//Set ac if not null
		if(ac !=null)
			button.setActionCommand(ac);
		
		//Set font size
		//button.setFont(new Font(button.getFont().getName(), button.getFont().getStyle(), BUTTON_FONT_SIZE));
		button.setFont(button.getFont().deriveFont(BUTTON_FONT_SIZE));

		
		//set foreground colour if not null
		if (fg != null)
			button.setForeground(fg);
		
		//set background colour if not null
		if (bg != null)
			button.setBackground(bg);
					
		//add action listener, set to unfocusable, and return JButton
		button.addActionListener(handler);
		button.setFocusable(false);
		return button;
		
	}
	
	/**
	 * Responsible for handling all the events generated by the GUI,
	 * passes a formatted string to CalculatorModel for solving.
	 * 
	 * @author Daniel Spagnuolo
	 * @version 2
	 * @see java.awt.event.ActionListener;
	 * @since 1.8_60
	 * 
	 */
	private class Controller implements ActionListener{
		/** Boolean for keeping track of the float/int mode*/
		boolean intMode = false;
		
		/**String for holding action command text*/
		String acText;
		
		/**String for holding the floating point precision value*/
		String ftpPrecision = "Hundredths"; 
		
		/**Calcmodel object for perfoming calculations*/
		CalculatorModel calcmodel = new CalculatorModel(); 
		
		/**for inputting numbers after an operator has been clicked*/
		boolean opClicked = false;
		
		/**for clearing display after equals is clicked*/
		boolean eqClicked = false; 
		
		/**for building calculation string*/
		String operands =""; 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			acText = e.getActionCommand(); //gets action command
			
			//If checkbox is clicked
			if (acText == "Int"){ 
				if (intMode == true){ //toggle int mode
					intMode = false;
					if (!calcmodel.getErrorState()){ //if there is no error, change error label
						dotButton.setBackground(Color.BLUE);
						error.setBackground(Color.YELLOW);
						error.setText("F");
					}
				} else { //toggle int mode
					intMode = true;
					if (!calcmodel.getErrorState()){ // if there is no error, change error label
						dotButton.setBackground(Color.GRAY);
						error.setBackground(Color.GREEN);
						error.setText("I");
					}
					display.setText(display.getText().split("\\.")[0]); //split on decimal, keep left half.
				}
			}
			
			//If a radio button is clicked
			else if (acText == "Tenths" || acText == "Hundredths" || acText == "Sci"){ 
				ftpPrecision = acText; //set precision variable 
			}
			
			//If backspace is clicked
			else if (acText == "Backspace"){ 
				if (display.getText().length() > 0 ){ //if there is text occupying the display
					if (! display.getText().equals("0.0") && ! display.getText().equals("0")) //if display is not a zero 
						display.setText(display.getText().substring(0, display.getText().length()-1));//remove one char from display
				} 
				if (display.getText().length() == 0 || display.getText().equals("-")){ //if there is nothing in display after backspacing, display zero
					if (intMode){ //set zero based on int/float mode
						display.setText("0");
					} else { 
						display.setText("0.0");
					}
				}
			}
			
			//If equals is clicked
			else if (acText == "Equals"){ 
				if (!calcmodel.getErrorState()){ //if there is no error
					eqClicked = true; //equal has been clicked, remember this for when inputting the next number
					operands+=display.getText(); //add whatever is in the display to the operands string
					System.out.println(operands); //TESTING
					calcmodel.setStringOperands(operands); //Send the operands string to the calcmodel
					if (calcmodel.getErrorState()==false){
						if (!intMode){ //if in float mode
							switch(ftpPrecision){ //format output based on float precision
							case "Tenths":
								display.setText(String.format("%.1f", calcmodel.getResult()));
								break;
							case "Hundredths":
								display.setText(String.format("%.2f", calcmodel.getResult()));
								break;
							case "Sci"://toEngineeringString()
								display.setText(String.format("%6.6e",calcmodel.getResult()));
								break;
							}
						}else{ //in int mode
							display.setText(String.format("%.0f", calcmodel.getResult()));
						}
					}
					//if an error was returned, set error label.
					if (calcmodel.getErrorState()==true){
						display.setText("--");
						error.setText("E");
						error.setBackground(Color.RED);
					}
					operands = ""; //reset operands string
				}
			}
			
			//IF clear is clicked
			else if (acText == "Clear"){ 
				if (calcmodel.getErrorState()){ //if there is an error, clear it
					calcmodel.setErrorState(false);
					if (!intMode){ //reset error label based on current mode
						dotButton.setBackground(Color.BLUE);
						error.setBackground(Color.YELLOW);
						error.setText("F");
					} else {
						dotButton.setBackground(Color.GRAY);
						error.setBackground(Color.GREEN);
						error.setText("I");
					}
				}
				if (intMode){ //set to zero based on intmode
					display.setText("0");
				} else { 
					display.setText("0.0");
				}
				operands = ""; //reset operands
			}
			
			//If decimal is clicked
			else if (acText == "Decimal"){ 
				if (!intMode && !calcmodel.getErrorState()){ //decimal only works in float mode and when there is no error
					display.setText(display.getText() + "."); //append '.' to display
				}
			}
			
			//If positive/negative is clicked
			else if (acText == "\u00B1"){ 
				//create stringbuilder for char index editing
				StringBuilder displayString = new StringBuilder(display.getText());
				
				//if the first char is a negative, remove it.
				if (displayString.charAt(0)=='-'){
					displayString.deleteCharAt(0);
				}else{ //otherwise, insert an negative
					displayString.insert(0, '-');
				}
				display.setText(displayString.toString()); //update display
			}
			
			else if (acText.matches("[-+*/]")){ //If operand is clicked
				String sciConvert = ""; //string to hold science mode conversion
				opClicked = true; //remember that an operator has been clicked for next time a number is clicked
				
				//if list of operands is occupied, check for /* or */ before adding number in display and operator.
				if (operands.length() > 0){
					//if incoming operator is *, and last char in operands was a /, don't add number in display to operands sring 
					if(acText == "*" && operands.charAt(operands.length()-1)=='/'){ 
						operands += acText;
					} 
					//if incoming operator is /, and last char in operands was a *, don't add number in display to operands string
					else if (acText == "/" && operands.charAt(operands.length()-1)=='*'){
						operands += acText;
					}else{ //otherwise add operand normally
						operands += display.getText();
						operands += acText;
						
					}
				} else{ //otherwise operands list is empty (possibly returning from '=' sign press
					if (ftpPrecision == "Sci"){ //if in science precision,
						sciConvert = display.getText(); //get science formatted answer
						BigDecimal num = new BigDecimal(Float.parseFloat(sciConvert)); //convert to plain float
						sciConvert = num.toPlainString();
						operands += sciConvert; //add plain float to operands
					}else { //not in science mode, add numer in display to operand string
						operands += display.getText();
					}
					
					operands += acText; //add operator to operand string
				}
			}
			
			else{ //If anything else is clicked
				if (!calcmodel.getErrorState()){ //if not in an error state,
					if (eqClicked){ //if eq was just clicked, reset display
						eqClicked = false; //reset eqclicked variable
						display.setText("");
					}
					if (opClicked){ //if operator was just clicked, reset display
						opClicked=false; //reset opClicked variable
						display.setText("");
					}
					//if the dislplay is a zero
					if (display.getText().equals("0.0") || display.getText().equals("0")){
						display.setText(""); //reset display
						display.setText(display.getText() + acText); //add number to display
					}
					else{display.setText(display.getText() + acText);} //otherwise just add the number
				}
			}
		}
	}
}

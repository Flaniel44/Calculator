/*
File name: Calculator.java
Author: Danie Spagnuolo, 040 768 219
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1 Part 1
Date: October 7th, 2015
Professor: Svillen Ranev
Purpose: Main method. Call the Splash screen and the Calculator view.
*/
package calc;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Class contains the main for the program, which calls the slash screen
 * and calls the Calculator builder
 * 
 * @author Daniel Spagnuolo
 * @version 1
 * @see CalculatorView.java, CalculatorSplashScreen.java
 * @since 1.8_60
 */
public class Calculator {
	/**{@value #SPLASH_TIME} used for the slash screen lifetime */
	private static final int SPLASH_TIME = 60;//6000
	
	/**{@value #TITLE} used for the title of the JFrame */
	private static final String TITLE = "Calculator";
	
	/**{@value #INITIAL_WIDTH} used for the width of the JFrame */
	private static final int INITIAL_WIDTH = 330;
	
	/**{@value #INITIAL_HEIGHT} used for the height of the JFrame */
	private static final int INITIAL_HEIGHT = 400;

	public static void main(String[] args) {
		// Creating the splash screen and passing the screen time to the constructor
		CalculatorSplashScreen splash = new CalculatorSplashScreen(SPLASH_TIME);
		splash.showSplashWindow();
		
		//creating the jPanel and GUI components
		CalculatorView calc = new CalculatorView();
		
		//call EventQueue.invokeLater() method with a Runnable instance the run() method
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				//Create frame for the calculator and set title
				JFrame frame = new JFrame(TITLE);
				//set the location and the size of the window
				Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
				int x = (screen.width-INITIAL_WIDTH)/2;
				int y = (screen.height-INITIAL_HEIGHT)/2;
				frame.setBounds(x,y,INITIAL_WIDTH,INITIAL_HEIGHT);
				
				frame.setVisible(true);
				
				//Prevennts the calculator from becoming too small
				frame.setMinimumSize(new Dimension(INITIAL_WIDTH, INITIAL_HEIGHT));
				
				//Add the Jpanel with GUI components
				frame.add(calc);
				
				//Set default close operation
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
			}
		});
	}
}

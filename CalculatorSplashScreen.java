/**
File name: CalculatorSplashScreen.java
Author: Svillen Ranev (slight modifications by Danie Spagnuolo) 040 768 219
Course: CST8221 – JAP, Lab Section: 302
Assignment: 1 Part 1
Date: October 7th, 2015
Professor: Svillen Ranev
Purpose: Build the splash screen for a relatively simple GUI for a Calculator Application.
 */
package calc;
import java.awt.*;
import javax.swing.*;

/**
 * Builds a splash screen to be displayed before the calculator GUI
 * 
 * @version 1
 * @see CalculatorView.java, Calculator.java
 * @since 1.8_60
 */
public class CalculatorSplashScreen extends JWindow {
/* Swing components are serializable and require serialVersionUID */
private static final long serialVersionUID = 6248477390124803341L;
private int duration;
/**
Default constructor. Sets the show time of the splash screen.
*/
public CalculatorSplashScreen(int duration) {
 this.duration = duration;
}
/**
Shows a splash screen in the center of the desktop
for the amount of time given in the constructor.
*/
public void showSplashWindow() {
	 ImageIcon imageIcon = new ImageIcon(this.getClass().getResource("SplashSwing.gif"));
//create content pane
  JPanel content = new JPanel(new BorderLayout());
// or use the window content pane
//  JPanel content = (JPanel)getContentPane();
  content.setBackground(Color.GRAY);
 
 // Set the window's bounds, position the window in the center of the screen
 int width =  imageIcon.getIconWidth();
 int height = imageIcon.getIconHeight();
 Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
 int x = (screen.width-width)/2;
 int y = (screen.height-height)/2;
 //set the location and the size of the window
 setBounds(x,y,width,height);

 // create the splash screen  
 JLabel label = new JLabel(imageIcon);

 JLabel demo = new JLabel("Daniel Spagnuolo", JLabel.CENTER);
 demo.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 14));
 content.add(label, BorderLayout.CENTER);
 content.add(demo, BorderLayout.SOUTH);
 // create custom RGB color
 Color customColor = new Color(44, 197, 211);
 content.setBorder(BorderFactory.createLineBorder(customColor, 10));
 
  //replace the window content pane with the content JPanel
   setContentPane(content);

 // Display it
 //make the splash window visible
 setVisible(true);

 // Wait a little while doing nothing, while the application is loading
 try {
 	
 	 Thread.sleep(duration); }
 catch (Exception e) {e.printStackTrace();}
 //destroy the window and release all resources
 dispose();
 }
}
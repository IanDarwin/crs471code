package com.learningtree.crs471;

import java.util.Properties;

/**
 *  This class is the main driver for the rainforest.web system. <p>
 *
 *  It creates the Main Frame which contains the music panels.
 *  It then displays the GUI frame to the user.
 *
 *  @author 471 Development Team
 */
public class RainForest {

	/**
	 *  Main method to create the RainForest program and display the GUI frame.
	 */
	public static void main(String[] args)  {

		// Alternate method of setting logging property
		// It has the same effect of the parameter "-Djava.util.logging.config.file"
		Properties prop = System.getProperties();
		prop.setProperty("java.util.logging.config.file", "logging.properties");

		MainFrame myFrame = new MainFrame();

		myFrame.setVisible(true);
	}

}
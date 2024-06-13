package com.learningtree.crs471;
import javax.swing.*;
import java.awt.*;

/**
 *  This class is the main frame for the RainForest application.
 *  It creates the MusicPanel and VideoPanel and adds them to
 *  the tabbed pane.  See the diagram below for basic layout.
 *
 *  <pre>
 *        _______  _______
 *       / Music \/ Video \____________________
 *       |                                     |
 *       |                                     |
 *       |                                     |
 *       |                                     |
 *       |_____________________________________|
 *
 *  </pre>
 *
 *
 *  @author 471 Development Team
 *
 */
public class MainFrame extends JFrame {

	/**
	 *  The tabbed pane to hold the music and
	 *  video panels
	 */
	protected JTabbedPane tabbedPane;

	/**
	 *  The music panel
	 */
	protected MusicPanel musicPanel;

	/**
	 *  The video panel
	 */
	protected VideoPanel videoPanel;

	/**
	 *  This constructor creates the MusicPanel and VideoPanel.  These
	 *  panels are added to a tabbed pane.
	 */
	public MainFrame() {

		//
		//  LAYOUT FRAME
		//
		setTitle("Welcome to rainforest.web!  v7.1");

		Image ltreeImage = Toolkit.getDefaultToolkit().getImage("images/ltree_icon.gif");
		setIconImage(ltreeImage);

		Container container = this.getContentPane();
		container.setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane();

		musicPanel = new MusicPanel(this);
		tabbedPane.addTab("Music", musicPanel);

		videoPanel = new VideoPanel(this);
		tabbedPane.addTab("Video", videoPanel);

		container.add(BorderLayout.CENTER, tabbedPane);

		setSize(500, 400);
		setLocation(100, 100);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 *  Hides the window and disposes of its resources.  Finally, we exit.
	 */
	public void exit() {

		setVisible(false);
		dispose();
		System.exit(0);
	}

}
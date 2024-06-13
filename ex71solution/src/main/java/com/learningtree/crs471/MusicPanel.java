package com.learningtree.crs471;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  This panel is responsible for displaying music recordings.  It contains
 *  a combo box for selecting the category.  Once a category is selected then
 *  a list of music recordings are displayed in a list box.
 *
 *  @author 471 Development Team
 *
 */
public class MusicPanel extends JPanel {

	protected JLabel	 	selectionLabel;
	protected JComboBox	 	categoryComboBox;
	protected JPanel 		topPanel;

	protected JList 		musicListBox;
	protected JScrollPane 	musicScrollPane;

	protected JButton 		detailsButton;
	protected JButton 		exitButton;
	protected JPanel 		bottomPanel;

	protected MainFrame 	parentFrame;

	protected MusicDataAccessor myDataAccessor;

	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public MusicPanel(MainFrame theParentFrame) {

		parentFrame = theParentFrame;

		myDataAccessor = new MusicDataAccessor();

		selectionLabel = new JLabel("Select a Music Category");

		// get categories
		List<String> categoryArrayList = myDataAccessor.getCategories();

		// populate category combo box
		categoryComboBox = new JComboBox();
		
		for (String temp : categoryArrayList) {
			categoryComboBox.addItem(temp);
		}

		categoryComboBox.addActionListener(new GoActionListener());

		// create the top panel
		topPanel = new JPanel();

		// create the list box and associated scroll panes
		musicListBox = new JList();
		musicListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musicScrollPane = new JScrollPane(musicListBox);

		detailsButton = new JButton("Details...");
		detailsButton.addActionListener(new DetailsActionListener());

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitActionListener());

		bottomPanel = new JPanel();

		// setting the layout
		this.setLayout(new BorderLayout());

		// set layout and add components for top panel
		topPanel.setLayout(new FlowLayout());
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);

		this.add(topPanel, BorderLayout.NORTH);

		this.add(musicScrollPane, BorderLayout.CENTER);

		// use the default flow layout for the bottom panel
		bottomPanel.add(detailsButton);
		bottomPanel.add(exitButton);

		this.add(bottomPanel, BorderLayout.SOUTH);

		// more bonus work - state management
		detailsButton.setEnabled(false);
		musicListBox.addListSelectionListener(new MusicListSelectionListener());

	}


	//
	// inner class
	//
	class GoActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {

			// Get the selected category from the "categoryComboBox"
			String category = (String) categoryComboBox.getSelectedItem();

			// Get the recordings
			List<MusicRecording> info = myDataAccessor.getRecordings(category);

			// Populate the list box
			musicListBox.setListData(info.toArray());
		}
	}

	/**
	 *  When the details button is pressed, we will
	 *
	 *  1.  Get the selected object the list box
	 *  2.  Create a details dialog for the music recording
	 *  3.  Show the details dialog
	 */
	class DetailsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			MusicRecording myMusicRecording = (MusicRecording) musicListBox.getSelectedValue();

			MusicDetailsDialog myDetailsDialog = new MusicDetailsDialog(parentFrame, myMusicRecording);

			myDetailsDialog.setVisible(true);
		}
	}


	/**
	 *  When called, let's call the exit routine of the parent frame
	 */
	class ExitActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			parentFrame.exit();
		}
	}


	/**
	 *  State management bonus.
	 *
	 *  The "Details" button is only enabled if an item is selected. <p>
	 *
	 *  The ListSelectionListener interface is defined in the java.swing.event package
	 */
	class MusicListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {

			if (musicListBox.isSelectionEmpty()) {
				detailsButton.setEnabled(false);
			}
			else {
				detailsButton.setEnabled(true);
			}
		}
	}

}

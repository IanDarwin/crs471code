package com.learningtree.crs471;
import javax.swing.*;
import javax.swing.event.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class VideoPanel extends JPanel {

	/**
	 *  The label to hold the text "Select a Video Category"
	 */
	protected JLabel selectionLabel;

	/**
	 *  The combo box to hold a list of video categories
	 */
	protected JComboBox categoryComboBox;


	/**
	 *  A panel to contain components.
	 */
	protected JPanel topPanel;

	/**
	 * List Box for displaying the video recordings
	 */
	protected JList videoListBox;

	/**
	 *  Supporting scroll pane for the videoListBox
	 */
	protected JScrollPane videoScrollPane;

	/**
	 *  Button labeled "Details"
	 */
	protected JButton detailsButton;


	/**
	 *  Button labeled "Exit"
	 */
	protected JButton exitButton;


	/**
	 *  A panel to contain components.
	 */
	protected JPanel bottomPanel;

	/**
	 *  A reference to the parent frame
	 */
	protected MainFrame parentFrame;

	/**
	 *  A holder for the list of video recordings
	 */
	protected ArrayList<VideoRecording> videoArrayList;

	/**
	 *  A reference video data accessor
	 */
	protected VideoDataAccessor myDataAccessor;


	/**
	 *  Creates the GUI components and arranges them
	 *  in the container.
	 */
	public VideoPanel(MainFrame theParentFrame) {

		parentFrame = theParentFrame;

		myDataAccessor = new VideoDataAccessor();

		selectionLabel = new JLabel("Select a Video Category");

		ArrayList<String> categoryArrayList = myDataAccessor.getCategories();

		// populate category combo box
		categoryComboBox = new JComboBox();
		categoryComboBox.addItem("---");
		for (String category : categoryArrayList) {
			categoryComboBox.addItem(category);
		}

		topPanel = new JPanel();

		videoListBox = new JList();
		videoListBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		videoScrollPane = new JScrollPane(videoListBox);

		// create the buttons for the bottom panel
		detailsButton = new JButton("Details...");
		exitButton = new JButton("Exit");

		bottomPanel = new JPanel();

		// set the layout for "this" VideoPanel
		this.setLayout(new BorderLayout());

		// set the layout for the topPanel and add components
		topPanel.setLayout(new FlowLayout());
		topPanel.add(selectionLabel);
		topPanel.add(categoryComboBox);

		// add the top panel to the northern region of "this" VideoPanel
		this.add(BorderLayout.NORTH, topPanel);

		// add the videoScrollPane to the center of "this" VideoPanel
		this.add(BorderLayout.CENTER, videoScrollPane);

		// set the layout for the bottomPanel and add components
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.add(detailsButton);
		bottomPanel.add(exitButton);

		// add the bottomPanel to the southern region of "this" VideoPanel
		this.add(BorderLayout.SOUTH, bottomPanel);

		//
		//  REGISTER LISTENERS
		//
		detailsButton.addActionListener(new DetailsActionListener());

		// bonus work
		exitButton.addActionListener(new ExitActionListener());
		categoryComboBox.addActionListener(new GoActionListener());
		videoListBox.addListSelectionListener(new VideoListSelectionListener());
		videoListBox.addMouseListener(new DoubleClickListener());

		// more bonus work - state management
		detailsButton.setEnabled(false);
	}


	/**
	 *  Populate the list box w/ the categories
	 *
	 *  <pre>
	 *
	 *    1.  If the selected category is not equal to "----" then
	 *        1a.  Get the video array list from data accessor
	 *    2.  Else create an empty video array list
	 *
	 *    3.  If the list is not empty then enable "Clear" button
	 *
	 *  </pre>
	 */
	protected void populateListBox() {

		String category = (String) categoryComboBox.getSelectedItem();

		if (category.startsWith("---")) {
			videoArrayList = new ArrayList<VideoRecording>();
		}
		else {
		videoArrayList = myDataAccessor.getRecordings(category);
		}

		Object[] theData = videoArrayList.toArray();
		videoListBox.setListData(theData);
	}

	/**
	 * Show details for a given index
	 */
	protected void showDetails(int recordingIndex) {

		// make sure the index is within the range
		if ((videoArrayList != null) &&
			(recordingIndex < videoArrayList.size()) &&
		    (recordingIndex >= 0)) {
			VideoRecording myVideoRecording = videoArrayList.get(recordingIndex);

			VideoDetailsDialog myDetailsDialog = new VideoDetailsDialog(parentFrame, myVideoRecording);

			myDetailsDialog.setVisible(true);
		}
	}

	//
	//  INNER CLASSES
	//

	/**
	 *  <pre>
	 *
	 *  When the go button is pressed, we will:
	 *
	 *  1.  Find out the selected category from combo box
	 *  2.  Get a list of CDs for the selected category from the data accessor
	 *  3.  Populate the list box w/ the cd artist and titles
	 *
	 *  </pre>
	 */
	class GoActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// the real work occurs in the populateListBox() method.
			populateListBox();
		}
	}

	/**
	 *  When the details button is pressed, we will
	 *
	 *  <pre>
	 *
	 *  1.  Get the selected item index from the list box
	 *  2.  Get the desired video from the array list
	 *  3.  Create a details dialog for the video
	 *  4.  Show the details dialog
	 *
	 *  </pre>
	 */
	class DetailsActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			int index = videoListBox.getSelectedIndex();
			showDetails(index);
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
	 *  When the "Clear" button is pressed, we will:
	 *
	 *  <pre>
	 *
	 *  1.  Clear the videoListBox
	 *  2.  Set the first category item as selected
	 *
	 *  </pre>
	 */
	class ClearActionListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			// create an empty array
			Object[] noData = new Object[1];

			// this will clear out the list
			videoListBox.setListData(noData);

			// set the first category item as selected
			categoryComboBox.setSelectedIndex(0);
		}
	}

	/**
	 *  State management bonus.
	 *
	 *  The "Details" button is only enabled if an item is selected. <p>
	 *
	 *  The ListSelectionListener interface is defined in the java.swing.event package
	 */
	class VideoListSelectionListener implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent event) {

			if (videoListBox.isSelectionEmpty()) {
				detailsButton.setEnabled(false);
			}
			else {
				detailsButton.setEnabled(true);
			}
		}
	}

	/**
	 * Handles double-clicks on the JList
	 */
	class DoubleClickListener extends MouseAdapter {

		public void mouseClicked(MouseEvent event){
			if(event.getClickCount() == 2){
				int index = videoListBox.locationToIndex(event.getPoint());
				videoListBox.ensureIndexIsVisible(index);
				showDetails(index);
			}
		}
   }
}
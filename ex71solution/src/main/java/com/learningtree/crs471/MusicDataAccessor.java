package com.learningtree.crs471;

import java.util.*;
import java.util.logging.Logger;
import java.sql.*;

/**
 * This class implements a trivial database for music recordings. Methods are
 * available to get a list of the music categories and a list of recordings.
 * <p>
 *
 * Usage Example:
 *
 * <pre>
 *
 *
 *
 *
 *       // create and load the data accessor
 *       MusicDataAccessor myDataAccessor = new MusicDataAccessor();
 *
 *       // get a list of available categories;
 *       ArrayList&lt;String&gt; cats = myDataAccessor.getCategories();
 *       ...
 *
 *       // get a list of jazz recordings
 *       ArrayList&lt;MusicRecording&gt; jazzRecordingList = myDataAccessor.getRecordings(&quot;Jazz&quot;);
 *       ...
 *
 *
 *
 *
 * </pre>
 *
 * @author 471 Development Team
 */
public class MusicDataAccessor {

	/**
	 * Logger for the messages
	 */
	private static Logger logger = Logger.getLogger("crs471");

	/**
	 * Constructs the data accessors.
	 */
	public MusicDataAccessor() {

		//  TO DO:  Complete the steps below.
		//
		//			For help, see the hints online

		try {
			// 1. Load database driver
			//         - driver name = com.mysql.jdbc.Driver		
			Class.forName("com.mysql.jdbc.Driver");

			logger.info("Loaded driver");
		} catch (Exception exc) {
			logger.severe(exc.toString());
		}
	}

	/**
	 * Returns a sorted list of the categories for the recordings.
	 */
	public ArrayList<String> getCategories() {
		
		//  TO DO:  Complete the steps below.
		//
		//			For help, see the hints online

		// 2. Create an empty ArrayList of <String> for the categories
		ArrayList<String> categories = new ArrayList<String>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rainforestdb", "student", "student");

			// 3. Execute query with the given sql
			String sql = "SELECT name FROM Music_Categories order by name asc";

			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery(sql);

			// 4. Process result and place data in the categories ArrayList
			while (myRs.next()) {
				String category = myRs.getString("name");
				categories.add(category);
			}

		}
		catch (SQLException exc) {
			logger.severe(exc.toString());
		}
		finally {
			try {
				if (myRs != null) {
					myRs.close();
				}

				if (myStmt != null) {
					myStmt.close();
				}
				
				if (myConn != null) {
					myConn.close();
				}
			}
			catch (Exception exc) {
				logger.severe(exc.toString());
			}
		}

		// 5. Return the categories ArrayList
		return categories;
	}

	/**
	 * Returns a sorted list of recordings that match a given category
	 *
	 * @param category
	 *            the category for requested recordings.
	 * @return collection of <code>MusicRecording</code> objects
	 */
	public ArrayList<MusicRecording> getRecordings(String category) {

		ArrayList<MusicRecording> recordingList = new ArrayList<MusicRecording>();

		logger.info("Getting a list of recordings for: " + category);

		String sql = "SELECT * FROM Music_Recordings where category='" + category + "' order by artist_name";

		Connection myConn = null;
		Statement myStmt = null;
		
		try {
			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rainforestdb", "student", "student");

			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				int recordingId = myRs.getInt("recording_id");
				String artist = myRs.getString("artist_name");
				String title = myRs.getString("title");
				String imageName = myRs.getString("image_name");
				double price = myRs.getDouble("price");

				Track[] trackList = getTracks(recordingId);
				MusicRecording tempRecording = new MusicRecording(artist, trackList, title, price, category, imageName);
				recordingList.add(tempRecording);
			}
		}
		catch (SQLException exc) {
			logger.severe(exc.toString());
		}
		finally {
			close(myStmt, myConn);
		}

		logger.info("getRecordings() complete!\n");

		return recordingList;
	}

	/**
	 * Helper method for reading tracks for a given recording id.
	 *
	 * @param recordingId
	 *            the recording id
	 *
	 * @return an array of <code>Track</code> objects
	 */
	protected Track[] getTracks(int recordingId) throws SQLException {

		ArrayList<Track> trackList = new ArrayList<Track>();

		String sql = "SELECT * FROM Music_Tracks where recording_id=" + recordingId;

		Connection myConn = null;
		Statement myStmt = null;
		
		try {

			myConn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/rainforestdb", "student", "student");

			myStmt = myConn.createStatement();
			ResultSet myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {
				String title = myRs.getString("title");
				int totalSeconds = myRs.getInt("duration");

				Duration tempDuration = new Duration(totalSeconds);
				Track tempTrack = new Track(title, tempDuration);
				trackList.add(tempTrack);
			}
		}
		catch (SQLException exc) {
			logger.severe(exc.toString());
		}
		finally {
			close(myStmt, myConn);
		}

		Track[] tracks = trackList.toArray(new Track[0]);
		return tracks;
	}

	/**
	 * Helper method to close statements
	 *
	 * @param theStatement
	 */
	protected void close(Statement theStatement, Connection myConn) {
		try {
			if (theStatement != null) {
				theStatement.close();
			}
			
			if (myConn != null) {
				myConn.close();
			}
			
		} catch (SQLException exc) {
			logger.severe(exc.toString());
		}
	}
}
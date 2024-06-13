package com.learningtree.crs471;
import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.net.*;

/**
 *  This class implements a trivial database for video recordings.
 *  Methods are available to get a list of the video categories and a
 *  list of recordings.   <p>
 *
 *  Usage Example:
 *  <pre>
 *
 *    // create and load the data accessor
 *    VideoDataAccessor myDataAccessor = new VideoDataAccessor();
 *
 *    // get a list of available categories;
 *    ArrayList<String> cats = myDataAccessor.getCategories();
 *    ...
 *
 *    // get a list of action videos
 *    ArrayList<VideoRecording> recordingList = myDataAccessor.getRecordings("Action");
 *    ...
 *
 *  </pre>
 *
 *  @author 471 Development Team
 */
public class VideoDataAccessor {


	//////////////////////////////////////////////////////
	//
	//  DATA FILE FORMAT:
	//
	//  The data file has the following format:
	//
	//    producer, video title, category, image name, rating, number minutes, year released, number of actors/actresses
	//    actor/actress #1
	//    actor/actress #2
	//    ----------------------------
	//
	//
	//  Here is a sample file:
	//
	//    Mel Gibson, Braveheart, Action & Adventure, braveheart.gif, R, 177, 2
	//    Mel Gibson
	//    Patrick McGoohan
	//    -----------------
	//
	//
	//  Note that each recording is ended w/ a line separator.  The contents
	//  of the separator is ignored during the read.  The reader simply consumes
	//  an extra line feed after reading a recording.
	//

	//
	/**
	 *  The name of the database file to read/write.
	 */
	protected static final String FILE_NAME = "video.db";

	/**
	 *  Marker used to separate the video recordings in the data file.
	 */
	protected static final String RECORD_SEPARATOR = "----------";

	/**
	 * Logger for the messages
	 */
	protected static Logger logger = Logger.getLogger("crs471");

	/**
	 * A HashMap/hashtable of the recordings.
	 *
	 * The key is the "category".
	 *
	 * The data stored for each key is an ArrayList which is the collection of
	 * music recordings.
	 */
	protected HashMap<String, ArrayList<VideoRecording>> dataTable;

	/**
	 * This holds a list of the recent recordings that have been added using the
	 * addRecording() method.
	 *
	 */
	protected ArrayList<VideoRecording> recentRecordingList;

	/**
	 * The list of categories
	 */
	protected ArrayList<String> categories;

	/**
	 * Internal helper
	 */
	protected HashSet<String> categorySet;

	/**
	 *  Constructs the data accessor and calls the load() method
	 *  to load data.
	 *
	 */
	public VideoDataAccessor() {

		// load the data into the table
		load();
	}

	String debug;

	/**
	 *  Loads the data from a storage device.
	 */
	public void load() {

		recentRecordingList = new ArrayList<VideoRecording>();
		categorySet = new HashSet<String>();

		dataTable = new HashMap<String, ArrayList<VideoRecording>>();

		ArrayList<VideoRecording> videoArrayList = null;
		StringTokenizer st = null;

		VideoRecording myRecording;
		String line = "";

		String producer, title;
		String category, imageName;
		String rating;
		int numberOfMinutes;
		int yearReleased;

		int numberOfActors;
		int basePrice;
		double price;  		// all of the recordings are the same LOW price :-)

		String[] actorList;

		try
		{
			// use the getResource() method for reading locally or over a network
			URL dataFileUrl = this.getClass().getResource(FILE_NAME);
			logger.fine("Loading File: " + dataFileUrl + "...");
			InputStream dataFileInputStream = dataFileUrl.openStream();
			BufferedReader inputFromFile = new BufferedReader(new InputStreamReader(dataFileInputStream));

			// read until end-of-file
			while ( (line = inputFromFile.readLine()) != null ) {

				// create a tokenizer for a comma delimited line
				st = new StringTokenizer(line, ",");

				//  Parse the info line to read following items formatted as
				//  -  the artist, title, category, imageName, rating, number of minutes, yearReleased, number of actors
				//
				producer = st.nextToken().trim();
				title = st.nextToken().trim();
				debug = title;

				category = st.nextToken().trim();
				categorySet.add(category);

				imageName = st.nextToken().trim();
				rating = st.nextToken().trim();
				numberOfMinutes = Integer.parseInt(st.nextToken().trim());
				yearReleased = Integer.parseInt(st.nextToken().trim());
				numberOfActors= Integer.parseInt(st.nextToken().trim());

				// read all of the actors in
				actorList = readActors(inputFromFile, numberOfActors);

				// create the video recording
				// select a random price between 9.99 and 24.99
				basePrice = 9 + (int) (Math.random() * 16);
				price = basePrice + .99;

				Duration duration = new Duration(0, numberOfMinutes, 0);
				myRecording = new VideoRecording(producer, actorList, rating, yearReleased, title,
												 price, category, imageName, duration);

				// check to see if we have information on this category
				if (dataTable.containsKey(category)) {

					// get the list of recordings for this category
					videoArrayList = dataTable.get(category);
				}
				else {

					// this is a new category.  simply add the category
					// to our dataTable
					videoArrayList = new ArrayList<VideoRecording>();
					dataTable.put(category, videoArrayList);
				}

				// add the recording
				videoArrayList.add(myRecording);

				// move ahead and consume the line separator
				line = inputFromFile.readLine();
			}

			inputFromFile.close();
			logger.fine("File loaded successfully!");

			// update the category list
			categories = new ArrayList<String>(categorySet);
			Collections.sort(categories);

			logger.fine("READY!\n");
		}
		catch (FileNotFoundException exc) {
			logger.fine("Could not find the file \"" + FILE_NAME + "\".");
			logger.fine("Make sure it is in the current directory.");
			logger.fine("========>>> PLEASE CONTACT THE INSTRUCTOR.\n\n\n");
			logger.severe(exc.toString());

		}
		catch (IOException exc) {
			logger.fine("IO error occurred while reading file: " + FILE_NAME);
			logger.fine("========>>> PLEASE CONTACT THE INSTRUCTOR.\n\n\n");
			logger.severe(exc.toString());
		}
		catch (Exception exc) {
			System.out.println(debug);
			exc.printStackTrace();
		}

	}

	/**
	 *  Helper method for reading a given number of actors from a Reader.
	 *
	 *  @param inputFromFile the reader for the file we are reading
	 *  @param numberOfActors the number of actors to read
	 *
	 *  @return an array of Actorss
	 */
	protected String[] readActors(BufferedReader inputFromFile, int numberOfActors)
		throws IOException
	{
		String[] actorList = new String[numberOfActors];

		StringTokenizer st;
		String actorLine;
		String actorName;

		for (int i=0; i < numberOfActors; i++)
		{
			actorLine = inputFromFile.readLine();

			st = new StringTokenizer(actorLine, ",");

			actorName = st.nextToken().trim();

			actorList[i] = actorName;
		}

		return actorList;
	}

	/**
	 * Returns a sorted list of recordings that match a given category
	 *
	 * @param category
	 *            the category for requested recordings.
	 * @return collection of <code>MusicRecording</code> objects
	 */
	public ArrayList<VideoRecording> getRecordings(String category) {

		ArrayList<VideoRecording> recordingList = new ArrayList<VideoRecording>();

		logger.fine("Getting a list of recordings for: " + category);

		// get a list of all recordings for this category
		if (dataTable.containsKey(category)) {
			recordingList = dataTable.get(category);
		} else {
			logger.fine("===>   NO RECORDINGS FOUND FOR: " + category);
		}

		logger.fine("getRecordings() complete!\n");

		return recordingList;
	}


	/**
	 * @return Returns the categories.
	 */
	public ArrayList<String> getCategories() {
		return categories;
	}
}
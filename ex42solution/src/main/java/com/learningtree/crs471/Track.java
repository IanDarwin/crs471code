package com.learningtree.crs471;
/**
 *  This class represents a track (ie a song).  This class describes
 *  the track's title and running time.
 *
 *   <pre>
 *     Usage Example:
 *
 *     Duration theRunningTime = new Duration(0, 4, 30);   // hr, min, sec
 *     Track firstTrack = new Track("Tooty Fruity", theRunningTime);
 *
 *   </pre>
 *
 *   @author 471 Development Team
 */
public class Track {

	//
	// FIELDS
	//

	/**
	 *  The track title
	 */
	private String title;

	/**
	 *  The track running time
	 */
	private Duration runningTime;

  	//
	// CONSTRUCTORS
  	//

	/**
	 *  Default constructor.  Simply creates an empty track.
	 */
	public Track() {
		title = "empty";
		runningTime = new Duration();
	}

  	/**
	 *  Creates a Track with the given parameter values
	 */
	public Track(String aTitle, Duration theRunningTime) {
		title = aTitle;
		runningTime = theRunningTime;
	}


  	//
	// METHODS
	//

	/**
	 *  Returns the title of the track
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets the title of the track
	 */
	public void setTitle(String	aTitle) {
		title = aTitle;
	}

	/**
	 *  Returns the running time of the track
	 */
	public Duration getRunningTime() {
		return runningTime;
	}

	/**
	 *  Sets the running time of the track
	 */
	public void setRunningTime(Duration theRunningTime) {
		runningTime = theRunningTime;
	}

	/**
	 *  Returns a string representation of the Track.  It
	 *  includes the track title and running time. <p>
	 *
	 *  The string has the following format:
	 *
	 *   <pre>
	 *    title, duration
	 *   </pre>
	 */
	public String toString() {

		int totalSeconds = runningTime.getTotalSeconds();
		int min = totalSeconds / 60;
		int secs = totalSeconds % 60;

		return String.format("%-30s %2d:%02d", title, min, secs);
	}
}
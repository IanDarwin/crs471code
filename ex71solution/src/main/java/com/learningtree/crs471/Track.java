package com.learningtree.crs471;
/**
 *  This class represents a track (ie a song).  This class describes
 *  the track's title and running time.
 *
 *   <pre>
 *     Usage Example:
 *
 *     Duration firstRunningTime = new Duration(0, 4, 30);   // hr, min, sec
 *     Track firstTrack = new Track("Tooty Fruity", firstRunningTime);
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
	 *  Returns the duration of the track
	 */
	public Duration getRunningTime() {
		return runningTime;
	}

	/**
	 *  Sets the duration of the track
	 */
	public void setRunningTime(Duration aDuration) {
		runningTime = aDuration;
	}

	/**
	 *  Returns a string representation of the Track.  It
	 *  includes the track title and running time. <p>
	 *
	 *  The string has the following format:
	 *
	 *   <pre>
	 *    title, running time
	 *   </pre>
	 */
	public String toString() {

		int totalSeconds = runningTime.getTotalSeconds();
		int min = totalSeconds / 60;
		int secs = totalSeconds % 60;

		String secsStr = "";

		// pad the seconds w/ leading zero if single digit
		if (secs < 10)
		{
			secsStr = "0";
		}

		secsStr += Integer.toString(secs);

		return title + ",  " + min + ":" + secsStr;
	}
}
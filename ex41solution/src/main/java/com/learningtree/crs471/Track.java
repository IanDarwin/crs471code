package com.learningtree.crs471;

/**
 * Ian's solution to Ex4.1
 * Accessors and toString() generated by Eclipse.
 */
public class Track {
	//
	//  TO DO: Complete the steps below.
	//	       For help, see the hints online
	//
	//
	//	4a.  Define the fields for the track title and runningTime
	//	 	-  The fields should have "private" access
	private String title;
	private Duration runningtime;

	//
	//	4b.  Define the getter and setter methods for the
	//		track title and runningTime
  	//		-  The methods should have "public" access
  	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Duration getRunningtime() {
		return runningtime;
	}

	public void setRunningtime(Duration runningtime) {
		this.runningtime = runningtime;
	}

	@Override
	public String toString() {
		return "Track [title=" + title + ", runningtime=" + runningtime + "]";
	}
}

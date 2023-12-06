
package com.learningtree.crs471;

import java.io.PrintWriter;

//
//  TO DO:  Complete the steps below.
//			For help, see the hints online
//
//
//  3a.  Modify the line below to define MusicRecording as a subclass of Recording
//
public class MusicRecording extends Recording {


	//
	//  TO DO:
	//
	//
	//	3b.  Define a "String" field for the artist name
	//		-  The field should have "private" access
	//
	//	3c.  Define an array field of "Track" objects..this is the track list
	//		-  The field should have "private" access
	//
	private String artistName;
	private Track[] trackList;


	public MusicRecording(String theArtist, Track[] theTrackList,
							String theTitle, double thePrice,
							String theCategory, String theImageName) {

		//
		//  TO DO:
		//
		//
		//	3d.  Initialize the fields of "MusicRecording" with the given
		//		parameter values
		//
		// Hint: you will need to call super(...) with arguments that
		// that match the superclass constructor. See course notes for
		// an example.
		super(theTitle, thePrice,
				theCategory, theImageName);
		artistName = theArtist;
		trackList = theTrackList;

	}



	//
	//  TO DO:
	//
	//
	//  3e.  Define a method to return the artist's name.
	//		This method should have the following signature:
	//		-	public String getArtist()
	public String getArtist() {
		return artistName;
	}
	//
	//  3f.  Define a method to return the track list.
	//		This method should have the following signature:
	//		-	public Track[] getTrackList()
	public Track[] getTrackList() {
		return trackList;
	}
	//
	//	3g.  Override the toString() method to return the artist name
	//		and recording title.  The string should be formatted as
	//
	//			artist, title
	//

	@Override
	public Duration getRunningTime() {
		int total = 0;
		for (Track t : trackList)
			total += t.getRunningTime().getTotalSeconds();
		return new Duration(total);
	}

	//@Override
	public Duration getRunningTimeAlt() {
		Duration d = new Duration();
		for (Track t : trackList) {
			d = d.add(t.getRunningTime());
		}
		return d;
	}

	@Override
	public String toString() {
		return artistName + ", " + getTitle();
	}
	
	// Bonus: Print the tracks.
	// NOT part of toString; printing all the tracks
	// violates the general contract of toString(), viz,
	// "a *compact* representation of the object's state"
	public void printTracks(PrintWriter out) {
		for (Track t : trackList) {
			out.println(t);
		}
	}
	
}

package com.learningtree.crs471;
public class Driver {

	public static void main(String[] args) {

		Track[] myTrackList = {
			new Track("Watching the Wheels", new Duration(0, 3, 48),
			new Track("Beautiful Boy", new Duration(0, 3, 30),
			new Track("Starting Over", new Duration(0, 4, 15)
		};

		MusicRecording myMusicRecording
					= new MusicRecording("John Lennon", myTrackList,
										 "Double Fantasy", 12.99,
										 "Rock", "");

		System.out.println(myMusicRecording);
		System.out.println("The tracks ");

		for (Track myTrack : myTrackList) {
			System.out.println(myTrack);
		}

		System.out.println("Total Duration = " + myMusicRecording.getRunningTime());
	}
}

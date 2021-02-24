package com.learningtree.crs471;
public class Driver {

	public static void main(String[] args) {

		Track[] myTrackList = new Track[3];

		Duration firstRunningTime = new Duration(0, 3, 48);
		myTrackList[0] = new Track("Watching the Wheels", firstRunningTime);

		Duration secondRunningTime = new Duration(0, 3, 30);
		myTrackList[1] = new Track("Beautiful Boy", secondRunningTime);

		Duration thirdRunningTime = new Duration(0, 4, 15);
		myTrackList[2] = new Track("Starting Over", thirdRunningTime);

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
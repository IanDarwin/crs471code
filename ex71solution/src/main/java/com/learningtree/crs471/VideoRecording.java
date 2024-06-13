package com.learningtree.crs471;
public class VideoRecording extends Recording {

	/**
	 *  Data members
	 */
	private String producer;
	private String[] actors;
	private String rating;
	private int yearReleased;
	private Duration myDuration;

	/**
	 *  Creates a VideoRecording object with given parameter values
	 */
	public VideoRecording(String theProducer, String[] theActors, String theRating, int theYearReleased,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName,
						  Duration theDuration) {

		super(theTitle, thePrice, theCategory, theImageName);

		producer = theProducer;
		actors = theActors;
		rating = theRating;
		yearReleased = theYearReleased;
		myDuration = theDuration;
	}

	public int getYearReleased() {
		return yearReleased;
	}

	public String getRating() {
		return rating;
	}
	public String getProducer() {
		return producer;
	}

	public String[] getActors() {
		return actors;
	}

	public Duration getRunningTime() {

		return myDuration;
	}

	/**
	 *  Returns the title of recording
	 */
	public String toString() {
		return getTitle();
	}


}
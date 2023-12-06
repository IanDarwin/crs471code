package com.learningtree.crs471;
/**
 *  This class represents a Recording.  It describes the recording
 *  title, price, category, imageName and running time.  <p>
 *
 *  Subclasses must implement the method <code>getRunningTime()</code> to
 *  return the total running time for the recording.
 *
 *  @author 471 Development Team
 */
public abstract class Recording implements Comparable<Recording> {

	//
	//  DATA MEMBERS
	//

	/**
	 *  The recording title
	 */
	private String title;

	/**
	 *  The recording price
	 */
	private double price;

	/**
	 *  The recording category
	 */
	private String category;

	/**
	 *  The recording image name
	 */
	private String imageName;


	//
	//  CONSTRUCTORS
	//

	/**
	 *  Basic default constructor
	 */
	public Recording() {
		// default constructor
	}



	/**
	 *  Constructs a recording w/ given parameter values.
	 */
	public Recording(String theTitle, double thePrice,
					 String theCategory, String theImageName) {

		title = theTitle;
		price = thePrice;
		category = theCategory;
		imageName = theImageName;
	}


	//
	//  GETTER / SETTER METHODS
	//

	/**
	 *  Returns the recording title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets the recording title
	 */
	public void setTitle(String theTitle) {
		title = theTitle;
	}


	/**
	 *  Returns the recording price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 *  Sets the recording price
	 */
	public void setPrice(double thePrice) {
		price = thePrice;
	}


	/**
	 *  Returns the recording category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 *  Sets the recording category
	 */
	public void setCategory(String theCategory) {
		category = theCategory;
	}


	/**
	 *  Returns the recording image name
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 *  Sets the recording image name
	 */
	public void setImageName(String theImageName) {
		imageName = theImageName;
	}


	//
	//  ABSTRACT METHOD(S)
	//

	/**
	 *  Returns the running time.  Subclasses must
	 *  override this method to return the total running time.
	 */
	public abstract Duration getRunningTime();


	/**
	 *  Allows us to sort the recordings by title
	 */
	public int compareTo(Recording recording) {

		String targetTitle = recording.getTitle();

		return title.compareTo(targetTitle);
	}

}

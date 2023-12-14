package com.learningtree.crs471;

/**
 * Ian's version of a solution to Ex4.1
 */
public class Driver {

	public static void main(String[] args) {

		//
		//  TO DO: Complete the steps below.

		//
		//
		//  6a.  Construct a new Track object and assign it to a variable
		Track t = new Track();
		//
		//  6b.  Set the title of your Track object to 
		t.setTitle("Watching The Wheels");
		//
		//  6c.  Set the running time of your Track object to 3 mins, 48 secs
		t.setRunningtime(new Duration(0,3,38));
		//
		//	6d.  Display the title and running time of your Track object
		System.out.println(t);
		
		// Bonus: Get title, minutes, seconds from CLI
		Track t2 = new Track();
		if (args.length != 3) {
			System.err.println("Usage: Driver title minutes seconds");
			return;
		}
		t2.setTitle(args[0]);
		int minutes = Integer.parseInt(args[1]);
		int seconds = Integer.parseInt(args[2]);
		t2.setRunningtime(new Duration(0, minutes, seconds));
		System.out.println(t2);
	}
}

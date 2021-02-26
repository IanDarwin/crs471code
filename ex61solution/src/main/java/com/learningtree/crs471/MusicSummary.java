package com.learningtree.crs471;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/** Exercise 6.1, recordings "db" (sorta) text file summary, 
 * done using this-century Java APIs
 * REQUIRES Java 11
 * @author Ian Darwin
 */
public class MusicSummary {

	static long recordingCount = 0;

	public static void main(String[] args) throws IOException {

		//
		//  Complete the steps below:
		//

		// Step 1. Read the file "music.db" a line at a time
		// Don't forget to handle exceptions! (Or shrug them upstairs)

		// A modern way, using Files.readAllLines and a Lambda
		Files.readAllLines(Path.of("music.db")).forEach(line -> {

			// Step 3. Increment recordingCount when the recording separator
			// String "----------------------" is found

			if (line.equals("----------------------")) {
				recordingCount += 1;
			}
		});

		// An even better way, using Java 8 Streams and Lambda:
		recordingCount = Files.readAllLines(Path.of("music.db"))
			.stream()
			.filter(line -> line.equals("----------------------"))
			.count();

		// Step 4. Print the number of recordings found
		String message = "Total recordings: " + recordingCount;
		System.out.println(message);

		// Major step 2:
		Files.writeString(Path.of("results.txt"), message);
	}
}


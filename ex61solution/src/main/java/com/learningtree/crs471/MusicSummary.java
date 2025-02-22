package com.learningtree.crs471;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** 
 * Exercise 6.1, recordings "db" (sorta) text file summary, 
 * done using this-century Java APIs
 * REQUIRES Java 8+
 * @author Ian Darwin
 */
public class MusicSummary {
	final static String SEP = "----------------------";
	static long count;

	public static void main(String[] args) throws IOException {

		// Major Step 1. Read the file "music.db" a line at a time
		// Don't forget to handle exceptions! (Or shrug them upstairs)

		// A modern way, using Files.readAllLines and a Lambda
		count = 0;
		Files.lines(Path.of("music.db")).forEach(line -> {

			// Step 3. Increment recordingCount when the recording separator
			// String "----------------------" is found

			if (line.equals("----------------------")) {
				count++;
			}
		});
		System.out.println(count);

		// An even better way, using Java 8 Streams and Lambda:
		count = Files.lines(Path.of("music.db"))
				.filter(line -> line.equals(SEP))
				.count();
		System.out.println(count);

		// Another way, working line-at-a-time
		count = 0;
		List<String> lines = Files.readAllLines(Path.of("music.db"));
		for (String line : lines) {
			if (line.equals(SEP)) {
				++count;
			}
		}
		System.out.println(count);

		// Finally, the legacy (Java 1.1) way:
		count = 0;
		try (BufferedReader is = new BufferedReader(new FileReader("music.db"));) {
			String line;
			while ((line = is.readLine()) != null) {
				if (line.equals(SEP)) {
					++count;
				}
			}
		}
		System.out.println(count);

		// Major step 2:
		Files.writeString(Path.of("results.txt"), Long.toString(count));
	}
}


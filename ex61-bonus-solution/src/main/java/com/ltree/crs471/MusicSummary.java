package com.ltree.crs471;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Ian's Bonus Solution for Ex61 - Recordings by Category.
 * (might be easier to use "select count categorized" in SQL :-)).
 */
public class MusicSummary {
	public static void main(String[] args) {
		//
		// Complete the steps below:
		//
		final String INFILE = "music.db";
		final String OUTFILE = "musicsummary.txt";

		int recordingCount = 0;

		// Step 1. Create a BufferedReader that reads from file "music.db"
		// Hint: you will need to create a FileReader also.
		// Don't forget to handle exceptions!

		// Map from category name to count
		Map<String, Integer> map = new HashMap<>();

		try (BufferedReader in = new BufferedReader(new FileReader(INFILE));
				PrintWriter pw = new PrintWriter(new File("results.text"));) {

			// Step 2. Read each line in the file, until end of file
			String line;
			boolean atStart = true;	// First recording lacks "----" so start true.

			while ((line = in.readLine()) != null) {

				if (atStart) {	// At start of a recording?
					String[] split = line.split(", ?");
					String category = split[2];
					int n = map.getOrDefault(category, 0);
					map.put(category, ++n);
					atStart = false;
				}

				// Step 3. Increment recordingCount when the recording separator
				if ("----------------------".equals(line)) {
					atStart = true;
					++recordingCount;
				}
			}

			// Step 4. Print the number of recordings found in each category, and total.
			for (String k : map.keySet()) {
				System.out.printf("Category %s count %d%n", k, map.get(k));
			}
			System.out.println("Total recordings: " + recordingCount);

			// MAJOR STEP 2 - output file (opened in try-with-resources above)
			pw.println(recordingCount);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}

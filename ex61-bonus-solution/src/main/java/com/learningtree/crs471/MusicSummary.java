package com.learningtree.crs471;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Ian's Bonus Solution for Ex61 - Recordings by Category.
 * (might be easier to use "select count categorized" in SQL,
 * but if we'd used a relational database, this example
 * would not be needed :-)).
 */
public class MusicSummary {
	final static String INFILE = "music.db";
	final static String OUTFILE = "musicsummary.txt";
	static final String SEPARATOR = "----------------------";
	static int recordingCount;
	// Map from category name to count (for bonus)
	final static Map<String, Integer> categoriesCount = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		
		// Step 1. Read the file "music.db" a line at a time.
		// Step 2. Read each line
		// Step 3. Count. This version calls doLine(line) on each line.
		
		// Steps 1 & 2 - Traditional way
		recordingCount = 0; categoriesCount.clear();
		try (BufferedReader is = new BufferedReader(new FileReader(INFILE))) {
			String line;
			while ((line = is.readLine()) != null) {
				doLine(line);
			}
		}

		// Steps 1 & 2 - Modern, Best practices way (a bit shorter, eh?)
		recordingCount = 0; categoriesCount.clear();
		Files.lines(Path.of(INFILE)).forEach(line -> doLine(line));
		
		// Bonus step: summarize categories
		for (String cat : categoriesCount.keySet()) {
			System.out.printf("Category %s has %d recordings\n", 
					cat, categoriesCount.get(cat));
		}
		
		// Step 4 - print total number of recordings to console
		var message = String.format("Total %d recordings", recordingCount);
		System.out.println(message);
		// And to our output file
		try (PrintWriter pw = new PrintWriter(OUTFILE);) {
			pw.println(message);
		}
	}

	private static boolean atStart = false;

	private static void doLine(String line) {
		// Step 3. Increment recordingCount when the recording separator found
		if (SEPARATOR.equals(line)) {
			atStart = true;
			++recordingCount;
		} else {
			if (atStart) {
				String[] fields = line.split(", *");
				System.out.printf("Found %d fields, first = %s\n", fields.length, fields[0]);
				String category = fields[2];
				categoriesCount.put(category, 
						categoriesCount.getOrDefault(category, 0) + 1);
			}
			atStart = false;
		}
	}
}



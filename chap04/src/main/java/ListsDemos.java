import java.util.*;

public class ListsDemos {

	private record MusicRecording(String artist, String title) {
		// Dummy version
	}

	// This is a List, we don't care what implementing class is returned!
	static List<MusicRecording> recordings = List.of(
		new MusicRecording("Java Cooks", "Greatest Hits"),
		new MusicRecording("The Selbies", "1-2-3 Rock"));

	public static void main(String[] args) {

		// This is the preferred loop for any List implementation
		// ArrayList, LinkedList, etc.
		for (MusicRecording rec : recordings) {
			System.out.println("Recording is " + rec);
		}
	}
}


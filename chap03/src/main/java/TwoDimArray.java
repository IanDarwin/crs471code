/**
 * Two-D arrays, created using array initializer,
 * iterated two ways.
 */
public class TwoDimArray {

	public static void main(String[] uselessArgs) {

		// Start of Hard way to populate
		//int[][] data = new int[4][3];
		
		// Better, easy way to populate
		// using Java "array initializer" syntax
		int[][] data = {
				{ 1, 2, 3 },
				{ 4, 5, 6, 7},
				{ 42 },
		};

		System.out.println("Indexed iteration");
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++) {
				System.out.println(data[i][j]);
			}
		}
		
		// Use this form if index isn't needed in loop
		System.out.println("Easier iteration");
		for (int[] row : data) {
			for (int num : row) {
				System.out.println(num);
			}
		}

	}
}

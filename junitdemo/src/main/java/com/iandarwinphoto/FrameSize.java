package com.iandarwinphoto;

public class FrameSize {

	/** Given a width, compute the height, assuming 8/10 proportion
	 * common in photo prints, eg., 4x5, 8/10, etc
	 */
	public static int getHeight(int width) {
		return (width * 8)/10;
	}
}

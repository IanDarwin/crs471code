package com.iandarwinphoto;

public class FrameSize {

	/** Given a width, compute the height, assuming 8/10 proportion */
	public static int getHeight(int width) {
		return (width * 8)/10;
	}
}

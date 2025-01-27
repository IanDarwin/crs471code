package com.iandarwinphoto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FrameSizeTest {

	@Test
	public void testGetHeight() {
		int width = 100;
		int expectedHeight = 80;
		assertEquals(expectedHeight, FrameSize.getHeight(width), 
				"getHeight");
	}
}

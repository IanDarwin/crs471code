package com.iandarwinphoto;

import org.junit.jupiter.*;
import static org.junit.jupiter.api.Assertions.*;

public class FrameSizeTest {

	@Test
	public void testGetHeight() {
		int width = 100;
		int expectedHeight = 80;
		assertEquals("getHeight", expectedHeight, FrameSize.getHeight(width));
	}
}

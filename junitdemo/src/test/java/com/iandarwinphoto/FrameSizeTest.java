package com.iandarwinphoto;

import org.junit.*;
import static org.junit.Assert.*;

public class FrameSizeTest {

	@Test
	public void testGetHeight() {
		int width = 100;
		int expectedHeight = 80;
		assertEquals("getHeight", expectedHeight, FrameSize.getHeight(width));
	}
}

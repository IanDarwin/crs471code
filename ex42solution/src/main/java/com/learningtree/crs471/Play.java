package com.learningtree.crs471;

public class Play {
	void demo() {
		try {
			int x = 42 / 0;
			System.out.println(x);
		} catch (RuntimeException e) {
			throw new IllegalArgumentException("Numeric error", e);
		}
	}
	
	void bury() {
		try {
			int y = 57 / 0;
			System.out.println(y);
		// NEVER DO THS:
		} catch (Exception e) {}
	}
}


package com.fingermatch.transformation;

public class Range {
	public static final Range zero = new Range(0, 0);
	public final int start;
	public final int end;
	public Range(int start, int end) {
		this.start = start;
		this.end = end;
	}
	public int length() {
		return end - start;
	}
}

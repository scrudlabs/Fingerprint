package com.fingermatch.transformation;

import java.nio.*;

public class DoubleMap {
	public final int width;
	public final int height;
	private final double[] array;

	public DoubleMap(int width, int height) {
		this.width = width;
		this.height = height;
		array = new double[width * height];
	}

	public DoubleMap(Cell size) {
		this(size.x, size.y);
	}

	public Cell size() {
		return new Cell(width, height);
	}

	public double get(int x, int y) {
		return array[offset(x, y)];
	}

	public double get(Cell at) {
		return get(at.x, at.y);
	}

	public void set(int x, int y, double value) {
		array[offset(x, y)] = value;
	}

	public void set(Cell at, double value) {
		set(at.x, at.y, value);
	}

	public void add(int x, int y, double value) {
		array[offset(x, y)] += value;
	}

	public void add(Cell at, double value) {
		add(at.x, at.y, value);
	}

	public void multiply(int x, int y, double value) {
		array[offset(x, y)] *= value;
	}

	public void multiply(Cell at, double value) {
		multiply(at.x, at.y, value);
	}

	public ByteBuffer serialize() {
		ByteBuffer buffer = ByteBuffer.allocate(8 * size().area());
		for (Cell at : size())
			buffer.putDouble(get(at));
		buffer.flip();
		return buffer;
	}

	public JsonArrayInfo json() {
		JsonArrayInfo info = new JsonArrayInfo();
		info.axes = new String[] { "y", "x" };
		info.dimensions = new int[] { height, width };
		info.scalar = "double";
		info.bitness = 64;
		info.endianness = "big";
		info.format = "IEEE754";
		return info;
	}

	private int offset(int x, int y) {
		return y * width + x;
	}
}

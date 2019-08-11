
package com.fingermatch.transformation;

import java.nio.*;

public class PointMap {
	public final int width;
	public final int height;
	private final double[] arrayX;
	private final double[] arrayY;

	public PointMap(int width, int height) {
		this.width = width;
		this.height = height;
		arrayX = new double[width * height];
		arrayY = new double[width * height];
	}

	public PointMap(Cell size) {
		this(size.x, size.y);
	}

	public Cell size() {
		return new Cell(width, height);
	}

	public Point get(int x, int y) {
		int i = offset(x, y);
		return new Point(arrayX[i], arrayY[i]);
	}

	public Point get(Cell at) {
		return get(at.x, at.y);
	}

	public void set(int x, int y, double px, double py) {
		int i = offset(x, y);
		arrayX[i] = px;
		arrayY[i] = py;
	}

	public void set(int x, int y, Point point) {
		set(x, y, point.x, point.y);
	}

	public void set(Cell at, Point point) {
		set(at.x, at.y, point);
	}

	public void add(int x, int y, double px, double py) {
		int i = offset(x, y);
		arrayX[i] += px;
		arrayY[i] += py;
	}

	public void add(int x, int y, Point point) {
		add(x, y, point.x, point.y);
	}

	public void add(Cell at, Point point) {
		add(at.x, at.y, point);
	}

	public ByteBuffer serialize() {
		ByteBuffer buffer = ByteBuffer.allocate(16 * size().area());
		for (Cell at : size()) {
			Point point = get(at);
			buffer.putDouble(point.x);
			buffer.putDouble(point.y);
		}
		buffer.flip();
		return buffer;
	}

	public JsonArrayInfo json() {
		JsonArrayInfo info = new JsonArrayInfo();
		info.axes = new String[] { "y", "x", "axis" };
		info.dimensions = new int[] { height, width, 2 };
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

package com.fingermatch.transformation;

import java.util.*;

public class Block implements Iterable<Cell> {
	public final int x;
	public final int y;
	public final int width;
	public final int height;

	public Block(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Block(Cell size) {
		this(0, 0, size.x, size.y);
	}

	public int left() {
		return x;
	}

	public int top() {
		return y;
	}

	public int right() {
		return x + width;
	}

	public int bottom() {
		return y + height;
	}

	public int area() {
		return width * height;
	}

	public static Block between(int startX, int startY, int endX, int endY) {
		return new Block(startX, startY, endX - startX, endY - startY);
	}

	public static Block between(Cell start, Cell end) {
		return between(start.x, start.y, end.x, end.y);
	}

	public static Block around(int x, int y, int radius) {
		return between(x - radius, y - radius, x + radius + 1, y + radius + 1);
	}

	public static Block around(Cell center, int radius) {
		return around(center.x, center.y, radius);
	}

	public Cell center() {
		return new Cell((right() + left()) / 2, (top() + bottom()) / 2);
	}

	public Block intersect(Block other) {
		return between(new Cell(Math.max(left(), other.left()), Math.max(top(), other.top())),
				new Cell(Math.min(right(), other.right()), Math.min(bottom(), other.bottom())));
	}

	public Block move(Cell delta) {
		return new Block(x + delta.x, y + delta.y, width, height);
	}

	private List<Object> fields() {
		return Arrays.asList(x, y, width, height);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof Block && fields().equals(((Block) obj).fields());
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, width, height);
	}

	@Override
	public String toString() {
		return String.format("[%d,%d] @ [%d,%d]", width, height, x, y);
	}

	@Override
	public Iterator<Cell> iterator() {
		return new BlockIterator();
	}

	private class BlockIterator implements Iterator<Cell> {
		int atX;
		int atY;

		@Override
		public boolean hasNext() {
			return atY < height && atX < width;
		}

		@Override
		public Cell next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Cell result = new Cell(x + atX, y + atY);
			++atX;
			if (atX >= width) {
				atX = 0;
				++atY;
			}
			return result;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

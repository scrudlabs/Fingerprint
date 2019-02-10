package com.fingermatch.transformation;

public class BlockGrid {
	public final Cell blocks;
	public final Cell corners;
	public final int[] x;
	public final int[] y;

	public BlockGrid(Cell size) {
		blocks = size;
		corners = new Cell(size.x + 1, size.y + 1);
		x = new int[size.x + 1];
		y = new int[size.y + 1];
	}

	public BlockGrid(int width, int height) {
		this(new Cell(width, height));
	}

	public Cell corner(int atX, int atY) {
		return new Cell(x[atX], y[atY]);
	}

	public Cell corner(Cell at) {
		return corner(at.x, at.y);
	}

	public Block block(int atX, int atY) {
		return Block.between(corner(atX, atY), corner(atX + 1, atY + 1));
	}

	public Block block(Cell at) {
		return block(at.x, at.y);
	}
}

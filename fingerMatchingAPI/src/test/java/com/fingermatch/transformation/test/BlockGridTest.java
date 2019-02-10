
package com.fingermatch.transformation.test; import com.fingermatch.transformation.*;

import static org.junit.Assert.*;
import org.junit.*;

public class BlockGridTest {
	private final BlockGrid g = new BlockGrid(3, 4);
	public BlockGridTest() {
		for (int i = 0; i < g.x.length; ++i)
			g.x[i] = (i + 1) * 10;
		for (int i = 0; i < g.y.length; ++i)
			g.y[i] = (i + 1) * 100;
	}
	@Test public void constructor() {
		assertEquals(4, g.x.length);
		assertEquals(5, g.y.length);
	}
	@Test public void constructorFromCell() {
		BlockGrid g = new BlockGrid(new Cell(2, 3));
		assertEquals(3, g.x.length);
		assertEquals(4, g.y.length);
	}
	@Test public void cornerAt() {
		assertEquals(new Cell(20, 300), g.corner(1, 2));
	}
	@Test public void cornerByCell() {
		assertEquals(new Cell(10, 200), g.corner(new Cell(0, 1)));
	}
	@Test public void blockAt() {
		assertEquals(new Block(20, 300, 10, 100), g.block(1, 2));
	}
	@Test public void blockByCell() {
		assertEquals(new Block(10, 200, 10, 100), g.block(0, 1));
	}
}

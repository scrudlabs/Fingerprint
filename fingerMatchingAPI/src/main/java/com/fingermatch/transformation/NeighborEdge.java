
package com.fingermatch.transformation;

public class NeighborEdge extends EdgeShape {
	final int neighbor;
	NeighborEdge(Minutia[] minutiae, int reference, int neighbor) {
		super(minutiae[reference], minutiae[neighbor]);
		this.neighbor = neighbor;
	}
}

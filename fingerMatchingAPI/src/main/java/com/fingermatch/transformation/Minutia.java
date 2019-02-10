
package com.fingermatch.transformation;

public class Minutia {
	public final Cell position;
	public final double direction;
	public final MinutiaType type;

	public Minutia(Cell position, double direction, MinutiaType type) {
		this.position = position;
		this.direction = direction;
		this.type = type;
	}

	public Minutia(JsonMinutia json) {
		position = new Cell(json.x, json.y);
		direction = json.direction;
		type = MinutiaType.BIFURCATION.json.equals(json.type) ? MinutiaType.BIFURCATION : MinutiaType.ENDING;
	}

	@Override
	public String toString() {
		return String.format("%s @ %s angle %f", type.toString(), position.toString(), direction);
	}
}

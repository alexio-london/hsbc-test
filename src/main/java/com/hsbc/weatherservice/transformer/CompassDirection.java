package com.hsbc.weatherservice.transformer;

/**
 * The enumeration of the 16 points of the compass
 *
 */
public enum CompassDirection {
	
	N,
	NNE,
	NE,
	ENE,
	E,
	ESE,
	SE,
	SSE,
	S,
	SSW,
	SW,
	WSW,
	W,
	WNW,
	NW,
	NNW;
	
	private static final CompassDirection[] ENUMS = CompassDirection.values();
	
	public static CompassDirection from(double degree) {
		return ENUMS[(int)(((degree + 11.25) % 360) / 22.5)];
	}
}

package com.hsbc.weatherservice.transformer;

import static java.util.stream.Collectors.toList;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implement the algorithm to calculate the average of a set of directions 
 *
 */
public class AverageDirection {

	public static double calculateAverageDirection(List<Double> directions) {
		
		// finds the minimum degree
		double minDegree = directions.stream().collect(Collectors.minBy(Comparator.naturalOrder())).get();
		
		// subtract the minimum to every angle so the set starts with a zero angle
		List<Double> radians = directions.stream()
				.map(e -> e - minDegree)
				.map(Math::toRadians)
				.collect(toList());
		
		// gets the average of the sin
		double sinAvg = radians.stream()
				.map(e -> Math.sin(e))
				.collect(Collectors.averagingDouble(e -> e)).floatValue();
		
		// gets the average of the cos
		double cosAvg = radians.stream()
				.map(e -> Math.cos(e))
				.collect(Collectors.averagingDouble(e -> e)).floatValue();
		
		// gets the angle represented by the averaged sin and cos
		return (Math.toDegrees(Math.atan2(sinAvg, cosAvg)) + minDegree + 360) % 360;
	}
}

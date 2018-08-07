package com.hsbc.weatherservice.transformer;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;


public class AverageDirectionTest {
	
	@Test
	public void testAverage() {
		
		List<Double> directions = Stream.of(10.0,20.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(15l));
	}	
	
	@Test
	public void testAverageAcrossZeroResultRight() {
		
		List<Double> directions = Stream.of(10.0,0.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(5l));
	}	

	@Test
	public void testAverageInput360() {
		
		List<Double> directions = Stream.of(10.0,360.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(5l));
	}	
	
	@Test
	public void testAverageAcrossZeroResultLeft() {
		
		List<Double> directions = Stream.of(10.0,330.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(350l));
	}		
	
	@Test
	public void testAverageAcross180Result180() {
		
		List<Double> directions = Stream.of(170.0,190.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(180l));
	}	
	
	@Test
	public void testAverageLeftOfZero() {
		
		List<Double> directions = Stream.of(0.0,330.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(345l));
	}
	
	@Test
	public void testSameValue() {
		
		List<Double> directions = Stream.of(80.0,80.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(80l));
	}	
	
	@Test
	public void testEdgeCaseTo90() {
		
		List<Double> directions = Stream.of(0.0,180.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(90l));
	}	
	
	@Test
	public void testEdgeCaseTo180() {
		
		List<Double> directions = Stream.of(270.0,90.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(180l));
	}	
	
	@Test
	public void testEdgeCaseDiagonal() {
		
		List<Double> directions = Stream.of(20.0,200.0).collect(toList());
		double average = AverageDirection.calculateAverageDirection(directions);
		
		assertThat(Math.round(average), equalTo(110l));
	}
}

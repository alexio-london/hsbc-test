package com.hsbc.weatherservice.transformer;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CompassDirectionTest {
	

	@Test
	public void testNordNordWestRightBoundary() {
		CompassDirection dir = CompassDirection.from(360-11.26);
		assertThat(dir, equalTo(CompassDirection.NNW));
	}	
	
	@Test
	public void testNordLeftBoundary() {
		CompassDirection dir = CompassDirection.from(360-11.25);
		assertThat(dir, equalTo(CompassDirection.N));
	}

	@Test
	public void testNord() {
		CompassDirection dir = CompassDirection.from(0);
		assertThat(dir, equalTo(CompassDirection.N));
	}
	
	@Test
	public void testNordRightBoundary() {
		CompassDirection dir = CompassDirection.from(11.24);
		assertThat(dir, equalTo(CompassDirection.N));
	}

	@Test
	public void testNordNordEstLeftBoundary() {
		CompassDirection dir = CompassDirection.from(11.25);
		assertThat(dir, equalTo(CompassDirection.NNE));
	}

	@Test
	public void testNordNordEstRightBoundary() {
		CompassDirection dir = CompassDirection.from(33.74);
		assertThat(dir, equalTo(CompassDirection.NNE));
	}

	@Test
	public void testNordEstLeftBoundary() {
		CompassDirection dir = CompassDirection.from(33.75);
		assertThat(dir, equalTo(CompassDirection.NE));
	}

}

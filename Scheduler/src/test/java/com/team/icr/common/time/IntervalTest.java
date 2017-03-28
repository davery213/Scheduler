package com.team.icr.common.time;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntervalTest {

	private ZonedDateTime start;
	private ZonedDateTime end;
	private Interval intvl1;

	@Before
	public void setup() {
		this.start = ZonedDateTime.now(ZoneOffset.UTC);
		this.end = this.start.plusMinutes(10);
		this.intvl1 = Interval.create(this.start, this.end);
	}

	@Test
	public void testCalculateDuration() {
		final long duration = this.intvl1.calculateDurationAsSeconds();
		Assert.assertEquals("The duration does not match!", 600, duration);
	}

	@Test
	public void testContains() {
		final Interval containedIntvl = Interval.create(this.start.plusMinutes(3), this.end.minusMinutes(3));

		Assert.assertTrue("The interval is not fully contained!", this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testEqualStartContains() {
		final Interval containedIntvl = Interval.create(this.start, this.end.minusMinutes(3));

		Assert.assertTrue("The interval is not fully contained!", this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testEqualEndContains() {
		final Interval containedIntvl = Interval.create(this.start.plusMinutes(3), this.end);

		Assert.assertTrue("The interval is not fully contained!", this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testFailStartContains() {
		final Interval containedIntvl = Interval.create(this.start.minusNanos(1), this.end.minusMinutes(3));

		Assert.assertTrue("The interval is fully contained!", !this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testFailEndContains() {
		final Interval containedIntvl = Interval.create(this.start.plusMinutes(3), this.end.plusNanos(1));

		Assert.assertTrue("The interval is fully contained!", !this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testFailStartAndEndContains() {
		final Interval containedIntvl = Interval.create(this.start.minusNanos(1), this.end.plusNanos(1));

		Assert.assertTrue("The interval is fully contained!", !this.intvl1.fullyContainsInterval(containedIntvl));
	}

	@Test
	public void testContainsTime() {
		final ZonedDateTime time = this.start.plusMinutes(1);

		Assert.assertTrue("The time is not contained!", this.intvl1.containsTime(time));
	}

	@Test
	public void testEqualStartContainsTime() {
		final ZonedDateTime time = this.start;

		Assert.assertTrue("The time is not contained!", this.intvl1.containsTime(time));
	}

	@Test
	public void testEqualEndContainsTime() {
		final ZonedDateTime time = this.end;

		Assert.assertTrue("The time is not contained!", this.intvl1.containsTime(time));
	}

	@Test
	public void testFailStartContainsTime() {
		final ZonedDateTime time = this.start.minusNanos(1);

		Assert.assertTrue("The time is contained!", !this.intvl1.containsTime(time));
	}

	@Test
	public void testFailEndContainsTime() {
		final ZonedDateTime time = this.end.plusNanos(1);

		Assert.assertTrue("The time is contained!", !this.intvl1.containsTime(time));
	}

}

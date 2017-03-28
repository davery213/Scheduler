package com.team.icr.common.time;

import java.time.Duration;
import java.time.ZonedDateTime;

public class Interval implements Comparable<Interval> {

	private final ZonedDateTime startTime;
	private final ZonedDateTime endTime;

	private Interval(final ZonedDateTime startTime, final ZonedDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static Interval create(final ZonedDateTime startTime, final ZonedDateTime endTime) {
		return new Interval(startTime, endTime);
	}

	public long calculateDurationAsSeconds() {
		return Duration.between(this.startTime, this.endTime)
				.getSeconds();
	}

	public boolean fullyContainsInterval(final Interval intvl) {

		final ZonedDateTime intvlStart = intvl.startTime;
		if (this.startTime.isAfter(intvlStart)) {
			return false;
		}

		final ZonedDateTime intvlEnd = intvl.endTime;
		return this.endTime.isBefore(intvlEnd) ? false : true;
	}

	public boolean containsTime(final ZonedDateTime time) {
		return this.startTime.isAfter(time) || this.endTime.isBefore(time) ? false : true;
	}

	public ZonedDateTime getStartTime() {
		return this.startTime;
	}

	public ZonedDateTime getEndTime() {
		return this.endTime;
	}

	@Override
	public int compareTo(final Interval intvl) {

		if (this.startTime.isBefore(intvl.startTime)) {
			return -1;
		} else if (this.startTime.isAfter(intvl.startTime)) {
			return 1;
		} else {
			return this.endTime.compareTo(intvl.endTime);
		}
	}

}

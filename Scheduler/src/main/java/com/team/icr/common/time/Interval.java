package com.team.icr.common.time;

import java.time.Duration;
import java.time.LocalDateTime;

public class Interval implements Comparable<Interval> {

	private final LocalDateTime startTime;
	private final LocalDateTime endTime;

	private Interval(final LocalDateTime startTime, final LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static Interval create(final LocalDateTime startTime, final LocalDateTime endTime) {
		return new Interval(startTime, endTime);
	}

	public long calculateDurationAsSeconds() {
		return Duration.between(this.startTime, this.endTime)
				.getSeconds();
	}

	public boolean fullyContainsInterval(final Interval intvl) {

		final LocalDateTime intvlStart = intvl.startTime;
		if (this.startTime.isAfter(intvlStart)) {
			return false;
		}

		final LocalDateTime intvlEnd = intvl.endTime;
		return this.endTime.isBefore(intvlEnd) ? false : true;
	}

	public boolean containsTime(final LocalDateTime time) {
		return this.startTime.isAfter(time) || this.endTime.isBefore(time) ? false : true;
	}

	public Interval copy() {
		final LocalDateTime copyStart = LocalDateTime.of(this.startTime.toLocalDate(), this.startTime.toLocalTime());
		final LocalDateTime copyEnd = LocalDateTime.of(this.endTime.toLocalDate(), this.endTime.toLocalTime());

		return new Interval(copyStart, copyEnd);
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public LocalDateTime getEndTime() {
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

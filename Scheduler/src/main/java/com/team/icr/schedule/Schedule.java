package com.team.icr.schedule;

import com.team.icr.common.time.Interval;

public class Schedule {

	private final Interval interval;

	private Schedule(final Interval interval) {
		this.interval = interval;
	}

	public Schedule create(final Interval interval) {
		return new Schedule(interval);
	}

	public Interval getInterval() {
		return this.interval.copy();
	}

}

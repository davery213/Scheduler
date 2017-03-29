package com.team.icr.schedule.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.team.icr.task.entity.Task;

@Entity
public class ScheduledTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	@ManyToOne
	private Task task;

	public ScheduledTask() {
	}

	private ScheduledTask(final LocalDateTime startTime, final LocalDateTime endTime, final Task task) {
		this.task = task;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public static ScheduledTask create(final LocalDateTime startTime, final LocalDateTime endTime, final Task task) {
		return new ScheduledTask(startTime, endTime, task);
	}

	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}

	public LocalDateTime getStartTime() {
		return this.startTime;
	}

	public void setStartTime(final LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return this.endTime;
	}

	public void setEndTime(final LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(final Task task) {
		this.task = task;
	}
}

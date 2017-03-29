package com.team.icr.util;

import java.time.LocalDateTime;

import com.team.icr.task.entity.Task;

public class TaskCreator {

	public static final String TASK_NAME = "Golf";

	public static Task createTask(final int version) {
		final Task task = new Task();
		task.setName(TASK_NAME);
		task.setVersion(version);
		final LocalDateTime start = LocalDateTime.now();
		task.setStartTime(start);
		task.setEndTime(start.plusMinutes(20));
		task.setPriority(115);

		return task;
	}
}

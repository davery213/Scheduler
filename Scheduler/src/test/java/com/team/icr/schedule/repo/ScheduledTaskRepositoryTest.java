package com.team.icr.schedule.repo;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.team.icr.schedule.entity.ScheduledTask;
import com.team.icr.task.entity.Task;
import com.team.icr.task.repo.TaskRepository;
import com.team.icr.util.TaskCreator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduledTaskRepositoryTest {

	private Task defaultTask;
	@Autowired
	private TaskRepository taskRepo;

	@Autowired
	private ScheduledTaskRepository scheduledTaskRepo;

	private LocalDateTime startTime;
	private LocalDateTime endTime;

	@Before
	public void setup() {
		setupTaskDb();
		setupTimes();
	}

	private void setupTaskDb() {
		this.defaultTask = TaskCreator.createTask(1);
		this.taskRepo.deleteAll();
		this.taskRepo.save(this.defaultTask);
	}

	private void setupTimes() {
		this.startTime = LocalDateTime.now();
		this.endTime = this.startTime.plusSeconds(45);
	}

	@After
	public void tearDown() {
		this.scheduledTaskRepo.deleteAll();
		this.taskRepo.deleteAll();
	}

	@Test
	public void testCreateNewScheduledTask() {
		final ScheduledTask scheduledTask = ScheduledTask.create(this.startTime, this.endTime, this.defaultTask);

		final ScheduledTask createdTask = this.scheduledTaskRepo.save(scheduledTask);

		Assert.assertTrue("Id has not been created; Db save failed!", createdTask.getId() > 0);
	}

}

package com.team.icr;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.team.icr.task.controller.TaskControllerTest;
import com.team.icr.task.repo.TaskRepositoryTest;
import com.team.icr.task.service.TaskServiceTest;
import com.team.icr.user.controller.UserControllerTest;
import com.team.icr.user.repo.UserRepositoryTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UserRepositoryTest.class, UserControllerTest.class, TaskRepositoryTest.class,
		TaskServiceTest.class, TaskControllerTest.class })
public class SchedulerTestSuite {
}

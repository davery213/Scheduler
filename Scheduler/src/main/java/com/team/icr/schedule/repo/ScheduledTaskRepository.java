package com.team.icr.schedule.repo;

import org.springframework.data.repository.CrudRepository;

import com.team.icr.schedule.entity.ScheduledTask;

public interface ScheduledTaskRepository extends CrudRepository<ScheduledTask, Long> {

}

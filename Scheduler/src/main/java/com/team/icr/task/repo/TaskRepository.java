package com.team.icr.task.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.team.icr.task.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

	List<Task> findByNameOrderByVersionDesc(String name);
}

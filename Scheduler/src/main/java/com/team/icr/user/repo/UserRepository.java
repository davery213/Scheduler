package com.team.icr.user.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.team.icr.user.entity.User;

@Transactional
public interface UserRepository extends CrudRepository<User, Long> {

}

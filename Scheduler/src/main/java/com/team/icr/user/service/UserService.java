package com.team.icr.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.icr.user.entity.User;
import com.team.icr.user.entity.UserRole;
import com.team.icr.user.enums.Role;
import com.team.icr.user.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userDao;

	public User createNewUser(final String firstName, final String lastName) {
		final User newUser = new User(firstName, lastName, new UserRole(Role.ADMIN));
		return this.userDao.save(newUser);
	}

	public User findUserById(final long id) {
		return this.userDao.findOne(id);
	}
}

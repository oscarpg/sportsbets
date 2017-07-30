package com.rurallabs.sportsbets.business.services;

import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.exceptions.DuplicatedEmailException;
import com.rurallabs.sportsbets.business.exceptions.DuplicatedLoginException;

public interface UserService {

	List<User> findAll(final Locale locale);

	User find(final String login);

	User findByEmail(final String email);

	long countUsers();

	@Transactional
	User save(final String login, final String email, final String password)
			throws DuplicatedLoginException, DuplicatedEmailException;
	
	@Transactional
	String resetPassword(final String login, final boolean sendEmail);
	
	@Transactional
	User changePassword(final String login, final String oldPassword, final String newPassword);
	
	@Transactional
	void delete(final String login);
}

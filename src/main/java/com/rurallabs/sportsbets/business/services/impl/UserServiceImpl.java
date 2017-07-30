package com.rurallabs.sportsbets.business.services.impl;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rurallabs.sportsbets.business.entities.User;
import com.rurallabs.sportsbets.business.entities.comparators.UserComparator;
import com.rurallabs.sportsbets.business.entities.repositories.UserRepository;
import com.rurallabs.sportsbets.business.exceptions.DuplicatedEmailException;
import com.rurallabs.sportsbets.business.exceptions.DuplicatedLoginException;
import com.rurallabs.sportsbets.business.services.UserService;
import com.rurallabs.sportsbets.business.util.IterableUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncryptor passwordEncryptor;

	public UserServiceImpl() {
		super();
	}

	@Override
	public User find(final String login) {
		return this.userRepository.findOne(login);
	}

	@Override
	public User findByEmail(final String email) {
		return this.userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAll(final Locale locale) {
		final List<User> users = IterableUtils.toList(this.userRepository.findAll());
		Collections.sort(users, new UserComparator(locale));
		return users;
	}

	@Override
	public long countUsers() {
		return this.userRepository.count();
	}

	@Override
	public User save(final String login, final String email, final String password)
			throws DuplicatedLoginException, DuplicatedEmailException {

		final boolean userExists = this.userRepository.exists(login);

		if (userExists) {
			throw new DuplicatedLoginException(login);
		}

		final boolean userEmailExists = this.userRepository.existsByEmail(email);

		if (userEmailExists) {
			throw new DuplicatedEmailException(email);
		}

		final String encryptedPassword = this.passwordEncryptor.encryptPassword(password);

		final User user = new User();
		user.setLogin(login);
		user.setPassword(encryptedPassword);
		user.setEmail(email);
		user.setGlobalAdmin(false);
		user.setActive(true);

		return this.userRepository.save(user);
	}

	@Override
	public String resetPassword(final String login, final boolean sendEmail) {

		final String newPassword = RandomStringUtils.randomAlphanumeric(10);
		final String hashedNewPassword = this.passwordEncryptor.encryptPassword(newPassword);

		final User user = this.userRepository.findOne(login);
		user.setPassword(hashedNewPassword);

		if (sendEmail) {
			// TODO
			// this.emailService.sendNewPassword(user, newPassword,
			// this.baseUrl);
		}

		return newPassword;

	}

	@Override
	public User changePassword(final String login, final String oldPassword, final String newPassword) {

		final User user = this.userRepository.findOne(login);

		final String oldHashedPassword = user.getPassword();

		if (!this.passwordEncryptor.checkPassword(oldPassword, oldHashedPassword)) {
			// TODO
			// throw new InternalErrorException("Old password does not match!");
		}

		final String hashedNewPassword = this.passwordEncryptor.encryptPassword(newPassword);
		user.setPassword(hashedNewPassword);

		return user;

	}

	@Override
	public void delete(final String login) {
		this.userRepository.delete(login);
	}

}

package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IAdressService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IEmailService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserService implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IAdressService adressService;

	@Autowired
	private ITelephoneService telephoneService;

	@Autowired
	private IEmailService emailService;

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity getUserByCpf(String cpf) {
		Optional<UserEntity> user = getById((String) cpf);

		if (user.isEmpty()) {
			throw new IllegalArgumentException("O usuário com CPF " + cpf + " não existe!");
		}

		return user.get();
	}

	@Override
	public Optional<UserEntity> getById(Object id) {
		return userRepository.findById((String) id);
	}

	@Override
	public UserEntity remove(UserEntity object) {
		object.getAdress().getUsers().remove(object);
		object.getTelephones().forEach((telephone) -> telephone.getUsers().remove(object));
		userRepository.remove(object);
		adressService.removeIfOrphan(object);
		telephoneService.removeOrhans(object);
		return object;
	}

	@Override
	public UserEntity insert(UserEntity user) throws DataIntegrityViolationException {
		try {
			encodePassword(user);
			return userRepository.insert(user);
		} catch (DataIntegrityViolationException e) {
			throw insertOrUpdateErro(e, user);
		}
	}
	
	private void encodePassword(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
	}

	private DataIntegrityViolationException insertOrUpdateErro(DataIntegrityViolationException e, UserEntity user)
			throws DataIntegrityViolationException {
		if (e.getMessage().contains("PUBLIC.USERS(USER_CPF)")) {
			return new DataIntegrityViolationException("O cpf \"" + user.getCpf() + "\" já foi utilizado");
		}

		if (e.getMessage().contains("PUBLIC.EMAILS(ADRESS_EMAIL)")) {

			Set<EmailEntity> emails = new HashSet<EmailEntity>(emailService.getAll());
			log.error("erro email: " + e.getMessage());

			for (EmailEntity email : user.getEmails()) {
				if (emails.contains(email)) {
					return new DataIntegrityViolationException("O email \"" + email.getEmail() + "\" já foi utilizado");
				}
			}

		}
		
		if (e.getMessage().contains("PUBLIC.USERS(USER_USERNAME)")) {
			return new DataIntegrityViolationException("O nome de usuário \"" + user.getUsername() + "\" já foi utilizado");
		}

		return e;
	}

	@Override
	public UserEntity update(UserEntity user) throws DataIntegrityViolationException {
		try {

			UserEntity originalUser = userRepository.findById(user.getCpf()).get();

			if (user.fullyEquals(originalUser)) {
				return user;
			}
			
			if(passwordChanged(user, originalUser)) {
				encodePassword(user);
			}

			removeOrfhanEmails(user, originalUser);

			return userRepository.update(user);
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			throw insertOrUpdateErro(e, user);
		}
	}

	private boolean passwordChanged(UserEntity user, UserEntity originalUser) {
		return !user.getPassword().equals(originalUser.getPassword());
	}

	private void removeOrfhanEmails(UserEntity user, UserEntity originalUser) {
		if (!user.getEmails().equals(originalUser.getEmails())) {

			Set<EmailEntity> emailsExcluded = new HashSet<>(originalUser.getEmails());

			emailsExcluded.removeAll(user.getEmails());

			emailsExcluded.forEach((email) -> {
				emailService.remove(email);
			});
			originalUser.setEmails(user.getEmails());
		}
	}

	@Override
	public UserEntity getAuthenticatedUser(JSONObject json) {
		UserEntity user = getUserByUserName(json.getString("username"));
		authenticateUser(user.getPassword(), json.getString("password"));
		return user;
	}

	private void authenticateUser(String actualPassword, String receivedPassword) {
		if (!passwordEncoder.matches(receivedPassword, actualPassword)) {
			throw new IllegalArgumentException("Senha incorreta!");
		}
	}

	private UserEntity getUserByUserName(String userName) {
		Optional<UserEntity> user = userRepository.findByUserName(userName);

		if (user.isEmpty()) {
			throw new IllegalArgumentException("O usuário " + userName + " não existe!");
		}

		return user.get();
	}

}

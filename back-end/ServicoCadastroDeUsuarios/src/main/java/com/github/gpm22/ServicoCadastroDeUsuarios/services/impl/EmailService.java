package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IEmailService;

@Service
public class EmailService implements IEmailService {

	@Autowired
	IEmailRepository emailRepository;

	@Override
	public EmailEntity insert(EmailEntity email) {
		return emailRepository.insert(email);
	}

	@Override
	public Optional<EmailEntity> getById(Object id) {
		return emailRepository.findById((Long) id);
	}

	@Override
	public EmailEntity remove(EmailEntity object) {
		return emailRepository.remove(object);
	}

	@Override
	public EmailEntity update(EmailEntity object) {
		return emailRepository.update(object);
	}

	@Override
	public List<EmailEntity> getAll() {
		return emailRepository.findAll();
	}

	@Override
	public EmailEntity parser(JSONObject json) {
		
		EmailEntity email = new EmailEntity(json.getString("email"));
		
		try {
			email.setId(json.getLong("id"));
		} catch (Exception e) {
			
		}
		
		return email;
	}

}

package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.EmailRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IEmailService;

@Service
public class EmailService implements IEmailService{

	@Autowired
	EmailRepository emailRepository;

	@Override
	public EmailEntity insert(EmailEntity email) {
		return emailRepository.saveAndFlush(email);
	}

	@Override
	public Optional<EmailEntity> getById(Long id) {
		return emailRepository.findById(id);
	}
	
	
}

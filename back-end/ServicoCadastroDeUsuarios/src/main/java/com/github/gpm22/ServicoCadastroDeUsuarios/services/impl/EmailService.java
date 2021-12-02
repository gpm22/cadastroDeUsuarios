package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IEmailService;

@Service
public class EmailService implements IEmailService{

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
	
	
}

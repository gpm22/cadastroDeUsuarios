package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;

@Repository
public class EmailRepository implements IEmailRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public EmailEntity insert(EmailEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		return object;
	}

	@Override
	public Optional<EmailEntity> findById(Object id) {
		return Optional.of(entityManager.find(EmailEntity.class, (Long) id));
	}

}

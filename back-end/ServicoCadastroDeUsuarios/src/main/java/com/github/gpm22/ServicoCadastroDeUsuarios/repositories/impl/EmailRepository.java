package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class EmailRepository implements IEmailRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public EmailEntity insert(EmailEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@Transactional
	public Optional<EmailEntity> findById(Object id) {
		return Optional.ofNullable(entityManager.find(EmailEntity.class, (Long) id));
	}

	@Override
	@Transactional
	public EmailEntity remove(EmailEntity object) {
		log.info("remove email: " + object);
		if (entityManager.contains(object)) {
			log.info("remove if antes email: " + object);
			entityManager.remove(object);
			log.info("remove if depois email: " + object);
		} else {
			log.info("remove else antes email: " + object);
			EmailEntity ee = entityManager.getReference(object.getClass(), object.getId());
			entityManager.remove(ee);
			log.info("remove else depois email: " + object);
		}
		entityManager.flush();
		entityManager.clear();
		log.info("remove após flush e clear email: " + object);
		return object;
	}

	@Override
	@Transactional
	public EmailEntity update(EmailEntity object) {
		entityManager.merge(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EmailEntity> findAll() {
		return entityManager.createQuery("Select t from EmailEntity t").getResultList();
	}

}

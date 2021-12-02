package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;

@Repository
public class EmailRepository implements IEmailRepository {

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
	@Transactional
	public Optional<EmailEntity> findById(Object id) {
		return Optional.ofNullable(entityManager.find(EmailEntity.class, (Long) id));
	}

	@Override
	@Transactional
	public EmailEntity remove(EmailEntity object) {

		if (entityManager.contains(object)) {
	        entityManager.remove(object);
	    } else {
	    	EmailEntity ee = entityManager.getReference(object.getClass(), object.getId());
	        entityManager.remove(ee);
	    }
		entityManager.flush();
		return object;
	}

	@Override
	@Transactional
	public EmailEntity update(EmailEntity object) {
		entityManager.merge(object);
		entityManager.flush();
		return object;
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<EmailEntity> findAll() {
		return entityManager.createQuery("Select t from EmailEntity t").getResultList();
	}

}

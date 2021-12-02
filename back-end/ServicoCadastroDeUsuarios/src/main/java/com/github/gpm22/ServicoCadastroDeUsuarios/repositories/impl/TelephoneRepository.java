package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;

@Repository
public class TelephoneRepository implements ITelephoneRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public TelephoneEntity insert(TelephoneEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		return object;
	}

	@Override
	public Optional<TelephoneEntity> findById(Object id) {
		return Optional.of(entityManager.find(TelephoneEntity.class, (Long) id));
	}

}

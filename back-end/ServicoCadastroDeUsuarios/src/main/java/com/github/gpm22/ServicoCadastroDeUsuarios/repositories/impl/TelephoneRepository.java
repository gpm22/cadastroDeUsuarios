package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;

@Repository
public class TelephoneRepository implements ITelephoneRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public TelephoneEntity insert(TelephoneEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	public Optional<TelephoneEntity> findById(Object id) {
		return Optional.ofNullable(entityManager.find(TelephoneEntity.class, (Long) id));
	}

	@Override
	@Transactional
	public TelephoneEntity remove(TelephoneEntity object) {

		if (entityManager.contains(object)) {
			entityManager.remove(object);
		} else {
			TelephoneEntity ee = entityManager.getReference(object.getClass(), object.getId());
			entityManager.remove(ee);
		}
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@Transactional
	public TelephoneEntity update(TelephoneEntity object) {
		entityManager.merge(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TelephoneEntity> findAll() {
		return entityManager.createQuery("Select t from TelephoneEntity t").getResultList();
	}

}

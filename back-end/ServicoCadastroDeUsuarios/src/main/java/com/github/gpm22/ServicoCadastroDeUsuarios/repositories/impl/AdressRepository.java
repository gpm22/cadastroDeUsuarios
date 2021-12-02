package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;

@Repository
public class AdressRepository implements IAdressRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public AdressEntity insert(AdressEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		return object;
	}

	@Override
	public Optional<AdressEntity> findById(Object id) {
		return Optional.of(entityManager.find(AdressEntity.class, (Long) id));
	}

}

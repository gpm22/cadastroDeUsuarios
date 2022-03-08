package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class UserRepository implements IUserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public UserEntity insert(UserEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	public Optional<UserEntity> findById(Object id) {
		return Optional.ofNullable(entityManager.find(UserEntity.class, (String) id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> findAll() {
		return entityManager.createQuery("Select t from UserEntity t").getResultList();

	}

	@Override
	public Optional<UserEntity> findByUserName(String username) {
		try {
			return Optional.ofNullable(
					(UserEntity) entityManager.createQuery("Select t from UserEntity t where t.username = :username")
							.setParameter("username", username).getSingleResult());
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public UserEntity remove(UserEntity object) {

		if (entityManager.contains(object)) {
			entityManager.remove(object);
		} else {
			UserEntity ee = entityManager.getReference(object.getClass(), object.getCpf());
			entityManager.remove(ee);
		}
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@Transactional
	public UserEntity update(UserEntity object) {
		entityManager.merge(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

}

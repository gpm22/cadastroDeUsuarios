package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;

@Repository
public class UserRepository implements IUserRepository{
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	@Transactional
	public UserEntity insert(UserEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		return object;
	}

	@Override
	public Optional<UserEntity> findById(Object id) {
		return Optional.of(entityManager.find(UserEntity.class, (String) id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<UserEntity> findAll() {
		return entityManager.createQuery("Select t from UserEntity t").getResultList();
	}

	@Override
	public Optional<UserEntity> findByUserName(String userName) {
		return Optional.of((UserEntity) entityManager.createQuery("Select t from UserEntity t where t.userName = :userName").setParameter("userName", userName).getSingleResult());
	}

}

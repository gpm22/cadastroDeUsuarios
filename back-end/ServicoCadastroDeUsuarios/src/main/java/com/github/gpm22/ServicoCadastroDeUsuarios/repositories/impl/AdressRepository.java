package com.github.gpm22.ServicoCadastroDeUsuarios.repositories.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;

@Repository
public class AdressRepository implements IAdressRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public AdressEntity insert(AdressEntity object) {
		entityManager.persist(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	public Optional<AdressEntity> findById(Object id) {
		return Optional.ofNullable(entityManager.find(AdressEntity.class, (Long) id));
	}

	@Override
	@Transactional
	public AdressEntity remove(AdressEntity object) {

		if (entityManager.contains(object)) {
			entityManager.remove(object);
		} else {
			AdressEntity ee = entityManager.getReference(object.getClass(), object.getId());
			entityManager.remove(ee);
		}
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@Transactional
	public AdressEntity update(AdressEntity object) {
		entityManager.merge(object);
		entityManager.flush();
		entityManager.clear();
		return object;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<AdressEntity> findAll() {
		return entityManager.createQuery("Select t from AdressEntity t").getResultList();
	}

	@Override
	public Optional<AdressEntity> findExistingAdress(AdressEntity adress) {

		try {
			return executeSqlQueryToFindExistingAdress(adress);
		} catch (NoResultException e) {
			return Optional.empty();
		}
	}

	private Optional<AdressEntity> executeSqlQueryToFindExistingAdress(AdressEntity adress) {
		String sql = "Select t from AdressEntity t " + "where t.cep = :cep " + "and t.publicPlace = :publicPlace "
				+ "and t.district = :district " + "and t.city=:city " + "and t.uf = :uf "
				+ "and t.complement = :complement";
		return Optional.of((AdressEntity) entityManager.createQuery(sql).setParameter("cep", adress.getCep())
				.setParameter("publicPlace", adress.getPublicPlace()).setParameter("district", adress.getDistrict())
				.setParameter("city", adress.getCity()).setParameter("uf", adress.getUf())
				.setParameter("complement", adress.getComplement()).getSingleResult());
	}
}

package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;

@Service
public class TelephoneService implements ITelephoneService {

	@Autowired
	ITelephoneRepository telephoneRepository;

	@Override
	public TelephoneEntity insert(TelephoneEntity telephone) {
		return telephoneRepository.insert(telephone);
	}

	@Override
	public Optional<TelephoneEntity> getById(Object id) {
		return telephoneRepository.findById((Long) id);
	}

	@Override
	public TelephoneEntity remove(TelephoneEntity object) {
		return telephoneRepository.remove(object);
	}

	@Override
	public TelephoneEntity update(TelephoneEntity object) {
		return telephoneRepository.update(object);
	}

	@Override
	public List<TelephoneEntity> getAll() {
		return telephoneRepository.findAll();
	}

	@Override
	public int removeOrhans(Set<TelephoneEntity> object) {
		Set<TelephoneEntity> orphansTelephones = object.stream().filter(telephone -> telephone.getUsers().size() == 0)
				.collect(Collectors.toSet());

		return telephoneRepository.removeAll(orphansTelephones);
	}

	@Override
	public void changeNewlyInsertedTelephonesForExistingTelephones(Set<TelephoneEntity> telephones) {

		Set<TelephoneEntity> newlyInsertedTelephones = telephones.stream()
				.filter(telephone -> telephone.getId() == null).collect(Collectors.toSet());

		List<TelephoneEntity> existingTelephones = telephoneRepository.findAllByList(newlyInsertedTelephones);

		telephones.removeAll(existingTelephones);
		telephones.addAll(existingTelephones);

	}

}

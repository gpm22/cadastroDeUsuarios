package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
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
		Set<TelephoneEntity> userTelephones = new HashSet<>(object);

		int removedTelephones = 1;

		for (TelephoneEntity telephone : userTelephones) {
			if (telephone.getUsers().size() == 0) {
				remove(telephone);
				removedTelephones++;
			}
		}

		return removedTelephones;
	}

	@Override
	public void changeTelephonesForExistingTelephones(Set<TelephoneEntity> telephones) {

		Set<TelephoneEntity> telephonesWithoutId = telephones.stream().filter(telephone -> telephone.getId() == null)
				.collect(Collectors.toSet());

		List<TelephoneEntity> existingTelephones = telephoneRepository.findAllByList(telephonesWithoutId) ;

		for (TelephoneEntity telephone : existingTelephones) {
			telephones.remove(telephone);
			telephones.add(telephone);
		}

	}

}

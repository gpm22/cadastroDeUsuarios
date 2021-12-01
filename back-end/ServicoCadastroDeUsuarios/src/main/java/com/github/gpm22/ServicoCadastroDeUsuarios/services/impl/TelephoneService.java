package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.TelephoneRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;

@Service
public class TelephoneService implements ITelephoneService{
	
	@Autowired
	TelephoneRepository telephoneRepository;

	@Override
	public TelephoneEntity insert(TelephoneEntity telephone) {
		return telephoneRepository.saveAndFlush(telephone);
	}

	@Override
	public Optional<TelephoneEntity> getById(Long id) {
		return telephoneRepository.findById(id);
	}

}

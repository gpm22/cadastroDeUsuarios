package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
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
	public TelephoneEntity parser(JSONObject json) {
		log.info("telephone-parser - json " + json);
		TelephoneEntity telephone = new TelephoneEntity();
		telephone.setType(json.getString("type"));
		telephone.setNumber(json.getString("number"));
		
		try {
			telephone.setId(json.getLong("id"));
		} catch (Exception e) {
			
		}
		return telephone;
	}

}

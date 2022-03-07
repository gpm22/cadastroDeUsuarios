package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
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
	public Set<TelephoneEntity> parseAll(JSONArray jsons, UserEntity user) {
		
		Set<TelephoneEntity> telephones = new HashSet<>();
		
		jsons.forEach((json) -> {
			TelephoneEntity telephoneNew = parser((JSONObject) json);
			telephoneNew.getUsers().add(user);
			telephones.add(telephoneNew);
		});
		
		changeTelephonesForExistingTelephones(telephones);
		
		return telephones;
	}
	
	private void changeTelephonesForExistingTelephones(Set<TelephoneEntity> telephones) {
		List<TelephoneEntity> allTelephones = getAll();

		int telCount = 1;

		for (TelephoneEntity telephone : allTelephones) {
			if (telephones.contains(telephone)) {
				telephones.remove(telephone);
				telephones.add(telephone);
			}
			telCount++;
			if (telCount == telephones.size()) {
				break;
			}
		}
	}

	@Override
	public TelephoneEntity parser(JSONObject json) {
		TelephoneEntity telephone = new TelephoneEntity();
		telephone.setType(json.getString("type"));
		telephone.setNumber(json.getString("number"));
		
		try {
			telephone.setId(json.getLong("id"));
		} catch (Exception e) {
			
		}
		return telephone;
	}

	@Override
	public int clean(UserEntity object) {
		List<TelephoneEntity> telephonesAll = new ArrayList<>(getAll());
		Set<TelephoneEntity> telephonesUser = new HashSet<>(object.getTelephones());
		
		int telCount = 1;
		
		for(TelephoneEntity telephone: telephonesAll) {
			if(telephonesUser.contains(telephone) && telephone.getUsers().size() == 0) {
				remove(telephone);
				telCount++;
			}
			if(telCount == telephonesUser.size()) {
				break;
			}
		}
		
		return telCount;
	}

}

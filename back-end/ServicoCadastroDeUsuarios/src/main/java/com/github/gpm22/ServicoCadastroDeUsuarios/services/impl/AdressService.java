package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IAdressService;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class AdressService implements IAdressService {

	@Autowired
	IAdressRepository adressRepository;

	@Override
	public AdressEntity insert(AdressEntity adress) {
		return adressRepository.insert(adress);
	}

	@Override
	public Optional<AdressEntity> getById(Object id) {
		return adressRepository.findById((Long) id);
	}

	@Override
	public AdressEntity remove(AdressEntity object) {
		return adressRepository.remove(object);
	}

	@Override
	public AdressEntity update(AdressEntity object) {
		return adressRepository.update(object);
	}

	@Override
	public List<AdressEntity> getAll() {
		return adressRepository.findAll();
	}

	@Override
	public AdressEntity parser(JSONObject json) {
		AdressEntity adress = new AdressEntity();
		
		adress.setCep(json.getString("cep"));
		adress.setPublicPlace(json.getString("publicPlace"));
		adress.setDistrict(json.getString("district"));
		adress.setCity(json.getString("city"));
		adress.setUf(json.getString("uf"));
		
		try {
			adress.setId(json.getLong("id"));
		} catch (Exception e) {
			
		}

		return adress;
	}
}

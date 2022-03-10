package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
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

	public Optional<AdressEntity> existingAdress(AdressEntity adress) {
		return adressRepository.findExistingAdress(adress);
	}

	@Override
	public void removeIfOrphan(UserEntity object) {
		if (object.getAdress().getUsers().size() == 0) {
			remove(object.getAdress());
		}
	}
}

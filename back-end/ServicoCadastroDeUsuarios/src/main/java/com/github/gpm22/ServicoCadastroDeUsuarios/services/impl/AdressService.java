package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IAdressService;

@Service
public class AdressService implements IAdressService{

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
}

package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IAdressService;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IParser;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;


@Service
public class Parser implements IParser {

	@Autowired
	IAdressService adressService;

	@Autowired
	ITelephoneService telephoneService;

	@Override
	public UserEntity parseJsonToUser(JSONObject json) {
		UserEntity user = new UserEntity(json);

		AdressEntity adress = createAdressFromJson(json.getJSONObject("adress"));
		user.setAdress(adress);
		adress.getUsers().add(user);

		user.getTelephones().addAll(createTelephonesFromJSONArray(json.getJSONArray("telephones"), user));
		user.getEmails().addAll(parseJSONArrayToEmails(json.getJSONArray("emails"), user));

		return user;
	}

	private AdressEntity createAdressFromJson(JSONObject json) {
		AdressEntity adress = new AdressEntity(json);
		Optional<AdressEntity> existingAdress = adressService.existingAdress(adress);

		if (existingAdress.isPresent()) {
			return existingAdress.get();
		}

		return adress;
	}

	private Set<TelephoneEntity> createTelephonesFromJSONArray(JSONArray jsonArray, UserEntity user) {
		Set<TelephoneEntity> telephones = parseJSONArrayToTelephones(jsonArray, user);
		telephoneService.changeTelephonesForExistingTelephones(telephones);

		return telephones;
	}
	
	private Set<TelephoneEntity> parseJSONArrayToTelephones(JSONArray jsonArray, UserEntity user) {
		Set<TelephoneEntity> telephones = new HashSet<>();

		jsonArray.forEach((json) -> {
			TelephoneEntity telephoneNew = new TelephoneEntity((JSONObject) json);
			telephoneNew.getUsers().add(user);
			telephones.add(telephoneNew);
		});

		return telephones;
	}

	private Set<EmailEntity> parseJSONArrayToEmails(JSONArray jsonArray, UserEntity user) {
		Set<EmailEntity> emails = new HashSet<>();

		jsonArray.forEach((json) -> {
			EmailEntity email = new EmailEntity((JSONObject) json);
			email.setUser(user);
			user.getEmails().add(email);
		});

		return emails;
	}

}

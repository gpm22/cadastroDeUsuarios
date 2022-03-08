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
		UserEntity user = new UserEntity();

		user.setPersonalInfo(json);

		AdressEntity adress = createAdressFromJson(json.getJSONObject("adress"));
		user.setAdress(adress);
		adress.getUsers().add(user);

		user.getTelephones().addAll(createTelephonesFromJSONArray(json.getJSONArray("telephones"), user));
		user.getEmails().addAll(parseJSONArrayToEmails(json.getJSONArray("emails"), user));

		return user;
	}

	private AdressEntity createAdressFromJson(JSONObject json) {
		AdressEntity adress = parseJsonToAdress(json);
		Optional<AdressEntity> existingAdress = adressService.existingAdress(adress);

		if (existingAdress.isPresent()) {
			return existingAdress.get();
		}

		return adress;
	}

	private AdressEntity parseJsonToAdress(JSONObject json) {
		AdressEntity adress = new AdressEntity();

		adress.setCep(json.getString("cep"));
		adress.setPublicPlace(json.getString("publicPlace"));
		adress.setDistrict(json.getString("district"));
		adress.setCity(json.getString("city"));
		adress.setUf(json.getString("uf"));
		adress.setComplement(json.getString("complement"));

		try {
			adress.setId(json.getLong("id"));
		} catch (Exception e) {

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
			TelephoneEntity telephoneNew = parseJsonToTelephone((JSONObject) json);
			telephoneNew.getUsers().add(user);
			telephones.add(telephoneNew);
		});

		return telephones;
	}

	private TelephoneEntity parseJsonToTelephone(JSONObject json) {
		TelephoneEntity telephone = new TelephoneEntity();
		telephone.setType(json.getString("type"));
		telephone.setNumber(json.getString("number"));

		try {
			telephone.setId(json.getLong("id"));
		} catch (Exception e) {

		}
		return telephone;
	}

	private Set<EmailEntity> parseJSONArrayToEmails(JSONArray jsonArray, UserEntity user) {
		Set<EmailEntity> emails = new HashSet<>();

		jsonArray.forEach((json) -> {
			EmailEntity email = parserJsonToEmail((JSONObject) json);
			email.setUser(user);
			user.getEmails().add(email);
		});

		return emails;
	}

	private EmailEntity parserJsonToEmail(JSONObject json) {

		EmailEntity email = new EmailEntity(json.getString("email"));

		try {
			email.setId(json.getLong("id"));
		} catch (Exception e) {

		}

		return email;
	}

}

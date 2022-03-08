package com.github.gpm22.ServicoCadastroDeUsuarios.servicesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.ITelephoneService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class TestTelephoneService {

	@Autowired
	ITelephoneService telephoneService;
	
	@Test
	void testExistingTelephones() {
		Set<TelephoneEntity> telephones = createTelephones();

		Set<TelephoneEntity> telephonesWithoutId = telephones.stream().filter(telephone -> telephone.getId() == null)
				.collect(Collectors.toSet());
		
		telephones.retainAll(telephonesWithoutId);
		
		telephones.forEach(log::info);
		
		assertEquals(telephones.size(), telephonesWithoutId.size());
	}
	
	

	@Test
	void testTelephonesWithoutId() {

		Set<TelephoneEntity> telephones = createTelephones();

		Set<TelephoneEntity> telephonesWithoutId = telephones.stream().filter(telephone -> telephone.getId() == null)
				.collect(Collectors.toSet());
		
		telephonesWithoutId.forEach(log::info);
		
		assertEquals(telephones.size()/2, telephonesWithoutId.size());
	}

	private Set<TelephoneEntity> createTelephones() {
		Set<TelephoneEntity> telephones = new HashSet<TelephoneEntity>();

		for (int i = 0; i < 30; i++) {
			TelephoneEntity newTelephone = new TelephoneEntity();
			if (i % 2 == 0) {
				newTelephone.setId((long) i);
			}
			newTelephone.setNumber("" + i);
			newTelephone.setType("A");

			telephones.add(newTelephone);
		}

		return telephones;
	}

}

package com.github.gpm22.ServicoCadastroDeUsuarios.repositoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class TestTelephoneRepository {
	
	@Autowired
	ITelephoneRepository telephoneRepository;

	@Test
	void testFindAllByList() {
		List<TelephoneEntity> telephones =  createTelephones();
		
		List<TelephoneEntity> telephonesFromRepository = telephoneRepository.findAllByList(telephones);
		
		telephonesFromRepository.forEach(log::info);
		
		telephones.sort(Comparator.comparing(TelephoneEntity::getNumber));
		telephonesFromRepository.sort(Comparator.comparing(TelephoneEntity::getNumber));
		
		
		assertEquals(telephones.size(), telephonesFromRepository.size());
		
		for(int i=0; i<telephones.size(); i++) {
			assertEquals(telephones.get(i), telephonesFromRepository.get(i));
		}
	}
	
	
	private List<TelephoneEntity> createTelephones(){
		List<TelephoneEntity> telephones = new ArrayList<>();
		
		telephones.add(new TelephoneEntity("celular", "22222222222"));
		telephones.add(new TelephoneEntity("comercial", "1111111111"));
		
		return telephones;
	}

}

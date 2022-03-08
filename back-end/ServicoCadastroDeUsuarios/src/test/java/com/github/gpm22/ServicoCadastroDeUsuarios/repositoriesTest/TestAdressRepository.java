package com.github.gpm22.ServicoCadastroDeUsuarios.repositoriesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
class TestAdressRepository {

	@Autowired
	IAdressRepository adressRepository;

	@Test
	void testFindExistingAdress() {
		
		AdressEntity adress = createAdress();
		
		Optional<AdressEntity> adressFromRepository = adressRepository.findExistingAdress(adress);
		
		if(adressFromRepository.isEmpty()) {
			throw new RuntimeException("adressFromRepository está vazio");
		}
		
		log.info(adressFromRepository);
		
		assertEquals(adress, adressFromRepository.get());
		
	}

	private AdressEntity createAdress() {
		return new AdressEntity("00000000", "Rua 98 lote 89 casa 712", "Nova Galáxia Federal", "Rio Roxo Azulado", "OM",
				"");
	}

}

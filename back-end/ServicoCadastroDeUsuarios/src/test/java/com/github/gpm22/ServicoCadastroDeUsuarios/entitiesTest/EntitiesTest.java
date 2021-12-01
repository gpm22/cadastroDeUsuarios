package com.github.gpm22.ServicoCadastroDeUsuarios.entitiesTest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.AdressRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class EntitiesTest {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private AdressRepository adressRepository;
	
	@Test
	public void userServiceTest() {
		log.info("Iniciando userServiceTest");
		
		List<UserEntity> users = userService.getAll();
		
		users.forEach((u) -> log.info("usuário: " + u));
		
		UserEntity user = new UserEntity("0", "Caio", "caio01", "123456", "ordinary");
		AdressEntity adress = new AdressEntity("1", "2", "3", "4", "5");
		
		adressRepository.save(adress);
		
		user.setAdress(adressRepository.getById(adress.getId()));
		
		log.info(userService.insert(user));
		
		users = userService.getAll();
		
		users.forEach((u) -> log.info("usuário: " + u));
		
		
		log.info("Finalizando userServiceTest");
	}

}

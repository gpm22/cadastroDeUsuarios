package com.github.gpm22.ServicoCadastroDeUsuarios.entitiesTest;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IAdressRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IEmailRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.ITelephoneRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class EntitiesTest {
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IAdressRepository adressRepository;
	
	@Autowired
	private IEmailRepository emailRepository;
	
	@Autowired
	private ITelephoneRepository telephoneRepository;

	@Test
	public void userRepositoryTest() {
		log.info("Iniciando userServiceTest");

		List<UserEntity> users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));

		UserEntity user = new UserEntity("10", "Caio", "caio1", "123456", "ordinary");
		AdressEntity adress = new AdressEntity("1", "2", "3", "4", "5");
		EmailEntity email = new EmailEntity("aasasa@asasa.com");
		TelephoneEntity telephone = new TelephoneEntity("fixo", "21220190");
		
		email.setUser(user);
		adress.setUsers(new HashSet<UserEntity>());
		adress.getUsers().add(user);
		telephone.setUsers(new HashSet<UserEntity>());
		telephone.getUsers().add(user);
		
		user.setAdress(adress);
		user.setEmails(new HashSet<EmailEntity>());
		user.getEmails().add(email);
		
		user.setTelephones(new HashSet<TelephoneEntity>());
		user.getTelephones().add(telephone);

		log.info(userRepository.insert(user));

		users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));
		
		log.info(userRepository.findById("10"));
		log.info(userRepository.findByUserName("admin"));
		

		log.info("Finalizando userServiceTest");
	}

}

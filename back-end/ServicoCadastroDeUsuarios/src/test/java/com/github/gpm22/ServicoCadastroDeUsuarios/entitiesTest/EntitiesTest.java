package com.github.gpm22.ServicoCadastroDeUsuarios.entitiesTest;

import java.util.HashSet;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.AdressEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.EmailEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IParser;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class EntitiesTest {

	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private IParser parser;

	// @Test
	public void testarParser() {
		log.info("iniciando testarParser");
		String jsonString = "{\"cpf\":\"0\",\"name\":\"administrador\",\"username\":\"admin\",\"password\":\"123456\",\"role\":\"administrator\",\"emails\":[{\"id\":0,\"email\":\"askaoskoaks@aoskoaksa.com\"}],\"telephones\":[{\"id\":0,\"type\":\"fixo\",\"number\":\"32818210\"}],\"adress\":{\"id\":0,\"cep\":\"0000000\",\"publicPlace\":\"Rua 19 lote 08 casa 01\",\"district\":\"Nova Iguaçu\",\"city\":\"Rio Verde\",\"uf\":\"OM\", \"complement\":\"ao lado do posto de saúde\"}}";

		log.info("json parseado: " + parser.parseJsonToUser(new JSONObject(jsonString)));
		log.info("finalizando testarParser");

	}

	// @Test
	public void userRepositoryTest() {
		log.info("Iniciando userServiceTest");

		List<UserEntity> users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));

		UserEntity user = new UserEntity("10", "Caio", "caio1", "123456", "ordinary");
		AdressEntity adress = new AdressEntity("1", "2", "3", "4", "5", null);
		EmailEntity email = new EmailEntity("aasasa@asasa.com");
		TelephoneEntity telephone = new TelephoneEntity("fixo", "21220190");
		log.info("adress - " + adress);
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

		log.info("insert: " + userRepository.insert(user));

		users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));

		log.info("update: " + userRepository.update(new UserEntity("0", "Lorota", "caio1009", "123456", "ordinary")));

		users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));

		log.info("find: " + userRepository.findById("54").isPresent());

		log.info("remove: " + userRepository.remove(userRepository.findById("0").get()));

		users = userRepository.findAll();

		users.forEach((u) -> log.info("usuário: " + u));

		log.info("Finalizando userServiceTest");
	}

}

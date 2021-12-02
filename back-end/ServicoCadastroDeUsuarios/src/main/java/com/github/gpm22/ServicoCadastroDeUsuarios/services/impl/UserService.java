package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.IUserRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}
		
	@Override
	public UserEntity insert(UserEntity user) {	
		return userRepository.insert(user);
	}

	@Override
	public Optional<UserEntity> getById(Object cpf) {
		return userRepository.findById((String) cpf);
	}

	@Override
	public boolean authenticateUser(String userName, String password) {
		Optional<UserEntity> user = userRepository.findByUserName(userName);
		return user.get().getPassword().equals(password);
	}

}

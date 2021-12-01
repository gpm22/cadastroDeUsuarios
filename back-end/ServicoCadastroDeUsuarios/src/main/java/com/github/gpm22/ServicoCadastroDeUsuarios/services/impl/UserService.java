package com.github.gpm22.ServicoCadastroDeUsuarios.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.UserEntity;
import com.github.gpm22.ServicoCadastroDeUsuarios.repositories.UserRepository;
import com.github.gpm22.ServicoCadastroDeUsuarios.services.IUserService;

@Service
public class UserService implements IUserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public List<UserEntity> getAll() {
		return userRepository.findAll();
	}
		
	@Override
	public UserEntity insert(UserEntity user) {
		if(this.getAllCpfs().contains(user.getCpf())) {
			throw new DuplicateKeyException("O cpf " + user.getCpf() + " já foi utilizado.");
		}
		return userRepository.saveAndFlush(user);
	}
	
	private Set<String> getAllCpfs() {
		return userRepository.findAllCpfs();
	}

	@Override
	public Optional<UserEntity> getByCpf(String cpf) {
		return userRepository.findById(cpf);
	}

}

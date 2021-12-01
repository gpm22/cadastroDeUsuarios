package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class AdressEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String cep;
	private String publicPlace;
	private String district;
	private String city;
	private String uf;
	@OneToMany(mappedBy="users")
	private Set<UserEntity> users;
	
	
}
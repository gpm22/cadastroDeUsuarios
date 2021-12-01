package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class UserEntity{
	
	@Id
	private String cpf;
	private String name;
	private String userName;
	private String password;
	private String role;
	@OneToMany(mappedBy="emails")
	private Set<EmailEntity> emails;
	@ManyToMany
	@JoinTable(
			name = "users_telephones",
			joinColumns = @JoinColumn(name = "user_cpf"),
			inverseJoinColumns = @JoinColumn(name = "telephone_id"))
	private Set<TelephoneEntity> telephones;
	@ManyToOne()
	@JoinColumn(name="adress_id", nullable=false)
	private AdressEntity adress;
	
}
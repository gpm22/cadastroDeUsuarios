package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class UserEntity{
	
	@Id
	@Column(name="user_cpf")
	private String cpf;
	@Column(name="user_name")
	private String name;
	@Column(name="user_username")
	private String userName;
	@Column(name="user_password")
	private String password;
	@Column(name="user_role")
	private String role;
	@OneToMany(mappedBy="user")
	private Set<EmailEntity> emails;
	@ManyToMany
	@JoinTable(
			name = "users_telephones",
			joinColumns = @JoinColumn(name = "user_cpf"),
			inverseJoinColumns = @JoinColumn(name = "telephone_id"))
	private Set<TelephoneEntity> telephones;
	@ManyToOne
	@JoinColumn(name="adress_id")
	private AdressEntity adress;
	
}
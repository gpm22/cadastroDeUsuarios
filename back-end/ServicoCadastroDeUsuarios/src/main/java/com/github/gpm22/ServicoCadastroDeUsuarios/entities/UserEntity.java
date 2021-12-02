package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USERS")
public class UserEntity{
	
	@Id
	@Column(name="user_cpf")
	private String cpf;
	@Column(name="user_name")
	private String name;
	@Column(name="user_username", unique=true)
	private String userName;
	@Column(name="user_password")
	private String password;
	@Column(name="user_role")
	private String role;
	@OneToMany(fetch = FetchType.EAGER, mappedBy="user", cascade = CascadeType.ALL)
	private Set<EmailEntity> emails;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(
			name = "users_telephones",
			joinColumns = @JoinColumn(name = "user_cpf"),
			inverseJoinColumns = @JoinColumn(name = "telephone_id"))
	private Set<TelephoneEntity> telephones;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name="adress_id")
	private AdressEntity adress;
	
	
	public UserEntity(String cpf, String name, String userName, String password, String role) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.emails = new HashSet<>();
		this.telephones = new HashSet<>();
	}
	
	public UserEntity() {
		this.emails = new HashSet<>();
		this.telephones = new HashSet<>();
	}
	
	
}
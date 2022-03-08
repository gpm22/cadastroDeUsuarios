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

import org.json.JSONObject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "USERS")
public class UserEntity {

	@Id
	@Column(name = "user_cpf")
	private String cpf;
	@Column(name = "user_name")
	private String name;
	@Column(name = "user_username", unique = true)
	private String username;
	@Column(name = "user_password")
	private String password;
	@Column(name = "user_role")
	private String role;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<EmailEntity> emails;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinTable(name = "users_telephones", joinColumns = @JoinColumn(name = "user_cpf"), inverseJoinColumns = @JoinColumn(name = "telephone_id"))
	private Set<TelephoneEntity> telephones;
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "adress_id")
	private AdressEntity adress;

	public UserEntity(String cpf, String name, String userName, String password, String role) {
		super();
		this.cpf = cpf;
		this.name = name;
		this.username = userName;
		this.password = password;
		this.role = role;
		this.emails = new HashSet<>();
		this.telephones = new HashSet<>();
	}

	public UserEntity() {
		this.emails = new HashSet<>();
		this.telephones = new HashSet<>();
	}
	
	public UserEntity(JSONObject json) {
		this.cpf = json.getString("cpf");
		this.name = json.getString("name");
		this.username = json.getString("username");
		this.password =  json.getString("password");
		this.role = json.getString("role");
		this.emails = new HashSet<>();
		this.telephones = new HashSet<>();
	}

}
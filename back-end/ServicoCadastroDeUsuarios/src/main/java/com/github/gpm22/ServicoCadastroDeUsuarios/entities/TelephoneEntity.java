package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "TELEPHONES")
public class TelephoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@EqualsAndHashCode.Exclude
	@Column(name = "telephone_id")
	private Long id;
	@Column(name = "telephone_type")
	private String type;
	@Column(name = "telephone_number", unique = true)
	private String number;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Set<UserEntity> users;

	public TelephoneEntity(String type, String number) {
		super();
		this.type = type;
		this.number = number;
		this.users = new HashSet<>();
	}

	public TelephoneEntity() {
		this.users = new HashSet<>();
	}

	public TelephoneEntity(JSONObject json) {

		this.type = json.getString("type");
		this.number = json.getString("number");

		try {
			this.id = json.getLong("id");
		} catch (Exception e) {

		}

		this.users = new HashSet<>();
	}

}
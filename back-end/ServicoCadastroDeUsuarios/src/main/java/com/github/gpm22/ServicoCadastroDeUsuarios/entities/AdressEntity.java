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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="ADRESSES")
public class AdressEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@EqualsAndHashCode.Exclude
	@Column(name="adress_id")
	private Long id;
	@Column(name="adress_cep")
	private String cep;
	@Column(name="adress_publicplace")
	private String publicPlace;
	@Column(name="adress_complement")
	private String complement;
	@Column(name="adress_district")
	private String district;
	@Column(name="adress_city")
	private String city;
	@Column(name="adress_uf")
	private String uf;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@OneToMany( fetch = FetchType.EAGER, mappedBy="adress", cascade = CascadeType.PERSIST)
	private Set<UserEntity> users;
	
	public AdressEntity(String cep, String publicPlace, String district, String city, String uf, String complement) {
		super();
		this.cep = cep;
		this.publicPlace = publicPlace;
		this.district = district;
		this.city = city;
		this.uf = uf;
		this.complement = complement;
		this.users = new HashSet<>();
	}
	
	public AdressEntity() {
		this.users = new HashSet<>();
	}
	
	public AdressEntity(JSONObject json) {

		this.cep = json.getString("cep");
		this.publicPlace = json.getString("publicPlace");
		this.district = json.getString("district");
		this.city = json.getString("city");
		this.uf = json.getString("uf");
		this.complement = json.getString("complement");

		try {
			this.id = json.getLong("id");
		} catch (Exception e) {

		}
	}
	
}
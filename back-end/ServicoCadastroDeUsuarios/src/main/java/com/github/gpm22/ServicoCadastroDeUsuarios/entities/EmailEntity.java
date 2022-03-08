package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="EMAILS")
public class EmailEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@EqualsAndHashCode.Exclude
	@Column(name="email_id")
	private Long id;
	@Column(name="adress_email", unique=true)
	private String email;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="user_cpf")
	private UserEntity user;
	
	public EmailEntity(String email) {
		super();
		this.email = email;
	}
	
	public EmailEntity() {}
	
	public EmailEntity (JSONObject json) {

		this.email = json.getString("email");

		try {
			this.id =  json.getLong("id");
		} catch (Exception e) {

		}
	}
	
}
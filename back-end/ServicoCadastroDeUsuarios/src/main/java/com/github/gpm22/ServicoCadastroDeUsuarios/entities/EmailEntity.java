package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	@Column(name="email_id")
	private Long id;
	@Column(name="adress_email")
	private String email;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_cpf")
	private UserEntity user;
	
	public EmailEntity(String email) {
		super();
		this.email = email;
	}
	
	public EmailEntity() {}
	
}
package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="EMAILS")
public class EmailEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="email_id")
	private Long id;
	@Column(name="adress_email")
	private String email;
	@ManyToOne
	@JoinColumn(name="user_cpf", nullable=false)
	private UserEntity user;
	
}
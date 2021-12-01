package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class EmailEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String email;
	@ManyToOne
	@JoinColumn(name="user_cpf", nullable=false)
	private UserEntity user;
	
}
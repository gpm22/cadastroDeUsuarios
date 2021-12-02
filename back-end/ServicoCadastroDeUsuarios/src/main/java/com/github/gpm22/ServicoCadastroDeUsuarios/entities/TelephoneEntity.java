package com.github.gpm22.ServicoCadastroDeUsuarios.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name="TELEPHONES")
public class TelephoneEntity{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="telephone_id")
	private Long id;
	@Column(name="telephone_type")
	private String type;
	@Column(name="telephone_number")
	private String number;
	@JsonIgnore
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(cascade = CascadeType.PERSIST)
	private Set<UserEntity> users;
	
	public TelephoneEntity(String type, String number) {
		super();
		this.type = type;
		this.number = number;
	}
	
	public TelephoneEntity() {}
	
}
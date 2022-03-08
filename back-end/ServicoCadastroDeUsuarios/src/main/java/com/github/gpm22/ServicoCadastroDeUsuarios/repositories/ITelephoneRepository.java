package com.github.gpm22.ServicoCadastroDeUsuarios.repositories;

import java.util.Collection;
import java.util.List;

import com.github.gpm22.ServicoCadastroDeUsuarios.entities.TelephoneEntity;

public interface ITelephoneRepository extends IGenericRepository<TelephoneEntity> {

	List<TelephoneEntity> findAllByList(Collection<TelephoneEntity> telephones);

}

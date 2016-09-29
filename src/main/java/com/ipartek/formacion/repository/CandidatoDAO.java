package com.ipartek.formacion.repository;

import java.io.Serializable;
import java.util.List;

import com.ipartek.formacion.domain.Candidato;

public interface CandidatoDAO extends Serializable, DAOSetter {

	List<Candidato> getAll();

	Candidato getById(long id);

	List<Candidato> getByDni(String dni);

	boolean eliminar(long id);

	boolean insertar(Candidato candidato);

	boolean modificar(Candidato candidato);

}
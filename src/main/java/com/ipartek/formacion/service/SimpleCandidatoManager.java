package com.ipartek.formacion.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.formacion.domain.Candidato;
import com.ipartek.formacion.repository.CandidatoDAO;

@Service("candidatoManager")
public class SimpleCandidatoManager implements CandidatoManager {

	private static final Logger logger = LoggerFactory.getLogger(SimpleCandidatoManager.class);

	private static final long serialVersionUID = 1L;

	private List<Candidato> candidatos;

	Candidato candidato = null;

	@Autowired
	private CandidatoDAO candidatoDAOImp;

	@Override
	public List<Candidato> getAll() {
		this.candidatos = this.candidatoDAOImp.getAll();
		return this.candidatos;
	}

	@Override
	public Candidato getById(long id) {
		return this.candidatoDAOImp.getById(id);
	}

	@Override
	public List<Candidato> getByDni(String dni) {
		logger.trace("buscando=" + dni);
		return this.candidatoDAOImp.getByDni(dni);
	}

	@Override
	public boolean eliminar(long id) {

		boolean resul = this.candidatoDAOImp.eliminar(id);
		if (resul) {
			logger.info("eliminado id=" + id);
		} else {
			logger.error("No se puedo eliminar id=" + id);
		}
		return resul;

	}

	@Override
	public boolean insertar(Candidato candidato) {
		return this.candidatoDAOImp.insertar(candidato);
	}

	@Override
	public boolean modificar(Candidato candidato) {
		return this.candidatoDAOImp.modificar(candidato);
	}

}
package com.ipartek.formacion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ipartek.formacion.domain.Candidato;
import com.ipartek.formacion.repository.mapper.CandidatoMapper;

@Repository("candidatoDAOImp")
public class CandidatoDAOImp implements CandidatoDAO {

	private static final Logger logger = LoggerFactory.getLogger(CandidatoDAOImp.class);
	private static final long serialVersionUID = 1L;

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbctemplate;
	// private SimpleJdbcCall jdbcCall;

	@Autowired
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbctemplate = new JdbcTemplate(this.dataSource);
		// this.jdbcCall = new SimpleJdbcCall(this.dataSource);
	}

	@Override
	public List<Candidato> getAll() {
		List<Candidato> candidatos = new ArrayList<Candidato>();

		final String SQL = "SELECT id,dni,nombre,fecha_alta,fecha_modificacion,fecha_eliminacion FROM candidatos where fecha_eliminacion is null;";
		try {
			candidatos = this.jdbctemplate.query(SQL, new CandidatoMapper());
		} catch (final EmptyResultDataAccessException e) {
			logger.warn("No existen productos " + SQL);
			candidatos = new ArrayList<Candidato>();
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}

		return candidatos;
	}

	@Override
	public Candidato getById(long id) {
		Candidato candidato = null;
		// TODO CAMBIAR POR preparedStatement o callableStatement
		final String SQL = "SELECT id,nombre,dni,fecha_alta,fecha_modificacion,fecha_eliminacion FROM candidatos where id ="
				+ id;
		try {
			candidato = this.jdbctemplate.queryForObject(SQL, new CandidatoMapper());
		} catch (final EmptyResultDataAccessException e) {
			logger.warn("No existe candidato con id= " + id);
			candidato = null;
		} catch (final Exception e) {
			logger.error(e.getMessage());
			candidato = null;
		}

		return candidato;
	}

	@Override
	public boolean eliminar(long id) {

		boolean resul = false;

		final java.util.Date dt = new java.util.Date();

		final java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final String currentTime = sdf.format(dt);

		final String SQL = "UPDATE candidatos SET fecha_eliminacion ='" + currentTime + "' WHERE id =" + id;
		if (1 == this.jdbctemplate.update(SQL)) {
			resul = true;
		}

		return resul;

		/*
		 * boolean resul = false; // TODO CAMBIAR POR preparedStatement o
		 * callableStatement final String SQL =
		 * "DELETE FROM `candidatos` WHERE  `id`=" + id;
		 * 
		 * if (1 == this.jdbctemplate.update(SQL)) { resul = true; }
		 * 
		 * return resul;
		 */
	}

	@Override
	public boolean insertar(final Candidato candidato) {
		boolean resul = false;
		int affectedRows = 0;
		try {
			final KeyHolder keyHolder = new GeneratedKeyHolder();
			final String SQL = "INSERT INTO `candidatos` (`dni`, `nombre`) VALUES (? , ? )";
			affectedRows = this.jdbctemplate.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, candidato.getDni());
					ps.setString(2, candidato.getNombre());
					return ps;
				}
			}, keyHolder);

			candidato.setId(keyHolder.getKey().longValue());

			if (affectedRows == 1) {
				resul = true;
			}
		} catch (final DuplicateKeyException e) {
			logger.warn("Constraint violation " + e.getMessage());
		}
		return resul;
	}

	@Override
	public boolean modificar(Candidato candidato) {
		boolean resul = false;
		int affectedRows = 0;
		final String SQL = "UPDATE candidatos SET dni = ? , nombre= ? WHERE id = ?";
		affectedRows = this.jdbctemplate.update(SQL,
				new Object[] { candidato.getDni(), candidato.getNombre(), candidato.getId() });
		if (affectedRows != 0) {
			resul = true;
		}

		return resul;

	}

	@Override
	public List<Candidato> getByDni(String dni) {
		List<Candidato> candidatos = new ArrayList<Candidato>();
		// TODO CAMBIAR POR preparedStatement o callableStatement
		final String SQL = "SELECT id,dni,nombre,fecha_alta,fecha_modificacion FROM candidatos where dni ='" + dni
				+ "'";
		try {
			candidatos = this.jdbctemplate.query(SQL, new CandidatoMapper());
		} catch (final EmptyResultDataAccessException e) {
			logger.warn("No existe candidato con dni= " + dni);
			candidatos = null;
		} catch (final Exception e) {
			logger.error(e.getMessage());
			candidatos = null;
		}

		return candidatos;
	}

}

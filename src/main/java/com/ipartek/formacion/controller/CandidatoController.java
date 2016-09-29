package com.ipartek.formacion.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ipartek.formacion.domain.Candidato;
import com.ipartek.formacion.form.SearchItemForm;
import com.ipartek.formacion.service.CandidatoManager;;

@Controller
public class CandidatoController {
	private final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private CandidatoManager candidatoManager;

	public void setPersonaManager(CandidatoManager candidatoManager) {
		this.candidatoManager = candidatoManager;
	}

	/**
	 * Mostrar listado de todos los candidatos
	 *
	 * 
	 * @return ModelAndView view: "index.jsp", model: { ArrayList
	 *         &lt;Candidato&gt; "candidatos" , String "fecha" } OK
	 */
	@RequestMapping(value = "/candidato", method = RequestMethod.GET)
	public ModelAndView listar() throws ServletException, IOException {

		this.logger.info("procesando peticion para listar candidatos");

		final Map<String, Object> model = new HashMap<String, Object>();
		final SearchItemForm searchItemForm = new SearchItemForm();

		model.put("searchItemForm", searchItemForm);
		model.put("candidatos", this.candidatoManager.getAll());
		model.put("fecha", new Date().toString());

		this.logger.info("Listados candidatos");

		return new ModelAndView("candidato/index", model);

	}

	/**
	 * Crear candidato nuevo
	 *
	 * 
	 * @return ModelAndView view: "form.jsp", model: { Candidato candidato ,
	 *         boolean "isNew" }
	 * 
	 */
	@RequestMapping(value = "/candidato/nuevo", method = RequestMethod.GET)
	public ModelAndView crearNuevo() throws ServletException, IOException {

		this.logger.info("creando candidato nuevo");

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidato", new Candidato());
		model.put("isNew", true);

		return new ModelAndView("candidato/form", model);

	}

	/**
	 * 
	 * @param candidato
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping(value = "/candidato/save", method = RequestMethod.POST)
	public ModelAndView salvar(@Valid Candidato candidato, BindingResult bindingResult) {

		this.logger.trace("Salvando candidato....");

		String msg = "";
		final Map<String, Object> model = new HashMap<String, Object>();

		if (bindingResult.hasErrors()) {

			this.logger.debug("parametros no validos");

			if (candidato.getId() == 0) {
				model.put("isNew", true);
			} else {
				model.put("isNew", false);
			}

			model.put("candidato", candidato);

		} else {

			if (candidato.isNew()) {
				if (this.candidatoManager.insertar(candidato) == true) {
					msg = "Insertado candidato";
				} else {
					msg = "No se ha podido insertar";
				}
			} else {
				if (this.candidatoManager.modificar(candidato) == true) {
					msg = "Modificado candidato";
				}
			}

			model.put("candidato", candidato);
			model.put("msg", msg);

		}
		return new ModelAndView("candidato/form", model);
	}

	/**
	 * Mostrar detalle candidato por id
	 *
	 * @param id
	 * 
	 * @return ModelAndView view: "index.jsp", model: { Candidato "candidato" ,
	 *         Boolean "isNew" }
	 * 
	 */
	@RequestMapping(value = "/candidato/mostrar/{id}", method = RequestMethod.GET)
	public ModelAndView verDetalle(@PathVariable(value = "id") final long id) {
		this.logger.trace("Mostrando detalle candidato[" + id + "]....");

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("candidato", this.candidatoManager.getById(id));

		if (this.candidatoManager.getById(id).getId() == 0) {
			model.put("isNew", true);
		} else {
			model.put("isNew", false);
		}
		return new ModelAndView("candidato/form", model);
	}

	/**
	 * Eliminar candidato por id
	 * 
	 * @param id
	 * 
	 * @return ModelAndView view: "index.jsp", model: { ArrayList Candidato
	 *         candidatos , String "fecha", String "msg", String "msgError" }
	 * 
	 * @throws ServletException
	 * @throws IOException
	 * 
	 * 
	 */
	@RequestMapping(value = "/candidato/eliminar/{id}", method = RequestMethod.GET)
	public ModelAndView eliminar(@PathVariable(value = "id") final long id) throws ServletException, IOException {
		this.logger.trace("Eliminando candidato[" + id + "]....");

		boolean existeMsgEliminar = false;
		final String msgEliminadoError = "No eliminado candidato[" + id + "]";
		final String msgEliminado = "candidato[" + id + "] eliminado";
		final SearchItemForm searchItemForm = new SearchItemForm();

		if (this.candidatoManager.eliminar(id)) {
			this.logger.info(msgEliminado);
			existeMsgEliminar = true;
		} else {
			this.logger.warn(msgEliminadoError);
			existeMsgEliminar = true;
		}

		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("msgEliminado", msgEliminado);
		model.put("msgEliminadoError", msgEliminadoError);
		model.put("existeMsgEliminar", existeMsgEliminar);
		model.put("candidatos", this.candidatoManager.getAll());
		model.put("fecha", new Date().toString());
		model.put("searchItemForm", searchItemForm);

		return new ModelAndView("candidato/index", model);
	}

	/**
	 * Mostrar detalle candidato por dni
	 *
	 * @param dni
	 * @return
	 * 
	 * @return ModelAndView view: "index.jsp", model: { ArrayList
	 *         &lt;Candidato&gt; "candidatos" , String "fecha" }
	 * @throws IOException
	 * @throws ServletException
	 * 
	 */
	@RequestMapping(value = "/candidato/buscar/", method = RequestMethod.GET, params = "dni")
	public ModelAndView buscar(String dni) throws ServletException, IOException {
		this.logger.trace("Mostrando candidato[" + dni + "]....");

		final Map<String, Object> model = new HashMap<String, Object>();
		final SearchItemForm searchItemForm = new SearchItemForm();
		final String msgBusqueda = "Candidato no encontrado";
		boolean existeMsgCandidato = false;

		final List<Candidato> candidatosById = this.candidatoManager.getByDni(dni);

		model.put("searchItemForm", searchItemForm);
		model.put("candidatos", candidatosById);
		model.put("fecha", new Date().toString());

		if (candidatosById.size() == 0) {
			existeMsgCandidato = true;
			model.put("msgBusqueda", msgBusqueda);
		}
		model.put("existeMsgCandidato", existeMsgCandidato);
		this.logger.info("Listados candidatos");

		return new ModelAndView("candidato/index", model);
	}
}

package br.com.mjv.oficina.registro.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.mjv.oficina.problema.model.Problema;
import br.com.mjv.oficina.problema.model.ProblemaRowMapper;
import br.com.mjv.oficina.registro.model.Registro;
import br.com.mjv.oficina.registro.model.RegistroRowMapper;
import br.com.mjv.oficina.registroProblema.model.RegistroProblema;
import br.com.mjv.oficina.registroProblema.model.RegistroProblemaRowMapper;
import br.com.mjv.oficina.veiculo.dao.VeiculoDao;
import br.com.mjv.oficina.veiculo.model.Veiculo;

@Repository
public class RegistroDaoImpl implements RegistroDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroDao.class);
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private VeiculoDao veiculoDao;

	@Override
	@Transactional
	public Integer cadastrarRegistro(Registro registro, Integer[] problemas) {
		LOGGER.info("Inicio do método cadastrarRegistro");
		
		Problema problema = getProblemaById(problemas[0]);
		Veiculo veiculo = veiculoDao.getById(problema.getFkIdVeiculo());
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_REGISTRO").usingColumns("nomeCliente", "nomeVeiculo").usingGeneratedKeyColumns("idRegistro");
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("nomeCliente", registro.getNomeCliente()).addValue("nomeVeiculo", veiculo.getNome());
		Integer key = (Integer) insert.executeAndReturnKey(params);
		
		LOGGER.info("Fim do método cadastrarRegistro");
		return key;
	}
	
	@Override
	public List<Problema> getProblemaListByVeiculoId(Integer id) {
		String sql = "SELECT * FROM TB_PROBLEMA WHERE fkIdVeiculo = :idVeiculo";
		LOGGER.info("Inicio do método getProblemaListByVeiculoId");
		try {
			List<Problema> list = new ArrayList<>();
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idVeiculo", id);
			list.addAll(template.query(sql, param , new ProblemaRowMapper()));
			LOGGER.info("Fim do método getProblemaListByVeiculoId");
			return list;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getProblemaListByVeiculoId: " + e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public void linkarProblemas(Integer idProblema, Integer idRegistro) {
		LOGGER.info("Inicio do método linkarProblemas");
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_REGISTRO_PROBLEMA").usingColumns("fkIdRegistro", "fkIdProblema");
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("fkIdRegistro", idRegistro).addValue("fkIdProblema", idProblema);
		insert.execute(params);
		
		LOGGER.info("Fim do método linkarProblemas");		
	}

	@Override
	public List<Registro> getAllRegistros(String name, Date dataInicio, Date dataFim) {
		try {
			LOGGER.info("Inicio do método getAllRegistros");
			StringBuilder sql = new StringBuilder("SELECT * FROM TB_REGISTRO WHERE 1 = 1 ");
			MapSqlParameterSource param = new MapSqlParameterSource();
			
			if(!StringUtils.isEmpty(name)) {
				sql.append("AND nomeVeiculo = :nomeVeiculo ");
				param.addValue("nomeVeiculo", name);
			}
			
			if(dataInicio != null) {
				sql.append("AND DATA >= :inicio ");
				param.addValue("inicio", dataInicio);
			}
			
			if(dataFim != null) {
				sql.append("AND DATA <= :fim ");
				param.addValue("fim", dataFim);
			}
			
			sql.append("ORDER BY DATA DESC");
			 
			List<Registro> listRegistro = template.query(sql.toString(), param, new RegistroRowMapper());
			
			LOGGER.info("Fim do método getAllRegistros");
			return listRegistro;
		}catch (EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResulto do método getAllRegistros: " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<RegistroProblema> getRegistroProblemaListByRegistroId(Integer id) {
		String sql = "SELECT * FROM TB_REGISTRO_PROBLEMA WHERE fkIdRegistro = :idRegistro";
		try {
			LOGGER.info("Inicio do método getRegistroProblemaListByRegistroId");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idRegistro", id);
			List<RegistroProblema> registroProblema = template.query(sql, param , new RegistroProblemaRowMapper());
			LOGGER.info("Fim do método getRegistroProblemaListByRegistroId");
			return registroProblema;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getRegistroProblemaListByRegistroId: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Problema getProblemaById(Integer id) {
		String sql = "SELECT * FROM TB_PROBLEMA WHERE idProblema = :idProblema";
		try {
			LOGGER.info("Inicio do método getFirstProblemaByRegistroId");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idProblema", id);
			Problema problema = template.queryForObject(sql, param , new ProblemaRowMapper());
			LOGGER.info("Fim do método getFirstProblemaByRegistroId");
			return problema;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getFirstProblemaByRegistroId: " + e.getMessage());
			return null;
		}
	}

	@Override
	public Registro getById(Integer id) {
		String sql = "SELECT * FROM TB_REGISTRO WHERE idRegistro = :idRegistro";
		try {
			LOGGER.info("Inicio do método getById");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idRegistro", id);
			Registro registro = template.queryForObject(sql, param, new RegistroRowMapper());
			LOGGER.info("Fim do método getById");
			return registro;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getById: " + e.getMessage());
			return null;
		}
	}

	@Override
	public List<Problema> getProblemaListByRegistroId(Integer id) {
		String sql = "SELECT * FROM TB_PROBLEMA WHERE fkIdVeiculo = :idVeiculo";
		LOGGER.info("Inicio do método getProblemaListByVeiculoId");
		try {
			List<Problema> list = new ArrayList<>();
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idVeiculo", id);
			list.addAll(template.query(sql, param , new ProblemaRowMapper()));
			LOGGER.info("Fim do método getProblemaListByVeiculoId");
			return list;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getProblemaListByVeiculoId: " + e.getMessage());
			return null;
		}
	}
	
	

}

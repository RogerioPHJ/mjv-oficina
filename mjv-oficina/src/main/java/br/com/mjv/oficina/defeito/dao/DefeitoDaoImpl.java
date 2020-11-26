package br.com.mjv.oficina.defeito.dao;

import java.util.ArrayList;
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

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.model.DefeitoRowMapper;

@Repository
public class DefeitoDaoImpl implements DefeitoDao {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;

	private final Logger LOGGER = LoggerFactory.getLogger(DefeitoDaoImpl.class);

	@Override
	public List<Defeito> getAllDefeitos() {
		String sql = "SELECT * FROM TB_DEFEITO";
		List<Defeito> lista = new ArrayList<>();
		try {
			LOGGER.info("Inicio do método getAllDefeitos");
			
			lista.addAll(template.query(sql, new DefeitoRowMapper()));
			
			LOGGER.info("Fim do método getAllDefeitos");
			
			return lista;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getAllDefeitos: " + e.getMessage());
			return lista;
		}
	}

	@Override
	@Transactional
	public void cadastrarDefeito(Defeito defeito) {
		LOGGER.info("Inicio do método cadastrarDefeito");
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_DEFEITO").usingColumns("nome");
		
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("nome", defeito.getNome());
		
		insert.execute(params);
		
		LOGGER.info("Fim do método cadastrarDefeito");
	}

	@Override
	public Defeito getDefeitoFirstResultByName(String name) {
		String sql = "SELECT * FROM TB_DEFEITO WHERE nome = :nome";
		try {
			LOGGER.info("Inicio do método getDefeitoFirstResultByName");

			MapSqlParameterSource params = new MapSqlParameterSource().addValue("nome", name);
			Defeito defeito = template.queryForObject(sql, params, new DefeitoRowMapper());
			
			LOGGER.info("Fim do método getDefeitoFirstResultByName");
			return defeito;
		}catch (EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getDefeitoFirstResultByName: " + e.getMessage());
			return null;
		}
	}
	
	@Override
	public Defeito getById(Integer id) {
		String sql = "SELECT * FROM TB_DEFEITO WHERE idDefeito = :idDefeito";
		try {
			LOGGER.info("Inicio do método getById");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idDefeito", id);
			Defeito defeito = template.queryForObject(sql, param, new DefeitoRowMapper());
			LOGGER.info("Fim do método getById");
			return defeito;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getById: " + e.getMessage());
			return null;
		}
	}
	
}

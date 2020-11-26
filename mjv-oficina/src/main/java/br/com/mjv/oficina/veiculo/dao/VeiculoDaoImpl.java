package br.com.mjv.oficina.veiculo.dao;

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

import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.pecadefeito.dao.PecaDefeitoDao;
import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.model.VeiculoRowMapper;

@Repository
public class VeiculoDaoImpl implements VeiculoDao {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PecaDefeitoDao pecaDefeitoDao;
	
	private final Logger LOGGER = LoggerFactory.getLogger(VeiculoDaoImpl.class);
	
	@Override
	public List<Veiculo> getAllVeiculos() {
		String sql = "SELECT * FROM TB_VEICULO";
		List<Veiculo> list = new ArrayList<>();
		try {
			LOGGER.info("Inicio do método getAllVeiculos");
			list.addAll(template.query(sql, new VeiculoRowMapper()));
			LOGGER.info("Fim do método getAllVeiculos");
			return list;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult do método getAllVeiculos: " + e.getMessage());
			return list;
		}
	}

	@Override
	@Transactional
	public Integer cadastrarVeiculo(Veiculo veiculo) {
		LOGGER.info("Inicio do método cadastrarVeiculo");
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_VEICULO").usingColumns("nome").usingGeneratedKeyColumns("idVeiculo");
		MapSqlParameterSource params = new MapSqlParameterSource().addValue("nome", veiculo.getNome());
		Integer key = (Integer) insert.executeAndReturnKey(params);
		
		LOGGER.info("Fim do método cadastrarVeiculo");
		return key;
	}

	@Override
	public Veiculo getVeiculoFirstResultByName(String name) {
		String sql = "SELECT * FROM TB_VEICULO WHERE nome = :nome";
		try {
			LOGGER.info("Inicio do método getVeiculoFirstResultByName");

			MapSqlParameterSource params = new MapSqlParameterSource().addValue("nome", name);
			Veiculo veiculo = template.queryForObject(sql, params, new VeiculoRowMapper());
			
			LOGGER.info("Fim do método getVeiculoFirstResultByName");
			return veiculo;
		}catch (EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getVeiculoFirstResultByName: " + e.getMessage());
			return null;
		}
	}

	@Override
	@Transactional
	public void linkarPecas(List<Peca> list, Integer idVeiculo) {
		LOGGER.info("Inicio do método linkarPecas");
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_PECA_VEICULO").usingColumns("fkIdPeca", "fkIdVeiculo");
		
		for(Peca peca : list) {
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("fkIdPeca", peca.getIdPeca()).addValue("fkIdVeiculo", idVeiculo);
			
			insert.execute(param);
		}
		
		LOGGER.info("Fim do método linkarPecas");
	}
	
	@Override
	@Transactional
	public void linkarProblemas(List<Peca> listPeca, Integer idVeiculo) {
		LOGGER.info("Inicio do método linkarProblemas");
		
		SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource).withTableName("TB_PROBLEMA").usingColumns("fkIdDefeito", "fkIdPeca", "fkIdVeiculo");

		for(Peca peca : listPeca) {
			List<PecaDefeito> listPecaDefeito= pecaDefeitoDao.recuperarListDefeito(peca.getIdPeca());
			
			for(PecaDefeito pecaDefeito : listPecaDefeito) {
				MapSqlParameterSource param = new MapSqlParameterSource().addValue("fkIdDefeito", pecaDefeito.getFkIdDefeito()).addValue("fkIdPeca", peca.getIdPeca()).addValue("fkIdVeiculo", idVeiculo);
				insert.execute(param);
			}
			
		}
		LOGGER.info("Fim do método linkarProblemas");
		
	}
	
	@Override
	public Veiculo getById(Integer id) {
		String sql = "SELECT * FROM TB_VEICULO WHERE idVeiculo = :idVeiculo";
		try {
			LOGGER.info("Inicio do método getById");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idVeiculo", id);
			Veiculo veiculo = template.queryForObject(sql, param, new VeiculoRowMapper());
			LOGGER.info("Fim do método getById");
			return veiculo;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método getById: " + e.getMessage());
			return null;
		}
	}

}

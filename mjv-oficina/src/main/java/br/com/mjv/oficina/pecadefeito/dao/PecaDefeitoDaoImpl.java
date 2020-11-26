package br.com.mjv.oficina.pecadefeito.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;
import br.com.mjv.oficina.pecadefeito.model.PecaDefeitoRowMapper;

@Repository
public class PecaDefeitoDaoImpl implements PecaDefeitoDao {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private final Logger LOGGER = LoggerFactory.getLogger(PecaDefeitoDaoImpl.class);
	
	@Override
	public List<PecaDefeito> recuperarListDefeito(Integer idPeca) {
		String sql = "SELECT * FROM TB_DEFEITO_PECA WHERE fkIdPeca = :idPeca";
		List<PecaDefeito> list = new ArrayList<>();
		try {
			LOGGER.info("Inicio do método recuperarListDefeito");
			MapSqlParameterSource param = new MapSqlParameterSource().addValue("idPeca", idPeca);
			list.addAll(template.query(sql, param , new PecaDefeitoRowMapper()));
			LOGGER.info("Fim do método recuperarListDefeito");
			return list;
		}catch(EmptyResultDataAccessException e) {
			LOGGER.error("Erro emptyResult no método recuperarListDefeito: " + e.getMessage());
			return null;
		}
	}

}

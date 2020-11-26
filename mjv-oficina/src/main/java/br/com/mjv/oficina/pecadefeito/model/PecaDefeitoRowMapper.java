package br.com.mjv.oficina.pecadefeito.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;


import br.com.mjv.oficina.peca.model.PecaRowMapper;
/**
 * Classe de mapeamento do modelo {@link PecaDefeito} para a tabela TB_DEFEITO_PECA
 * @author rogerio.pontes
 *
 */
public class PecaDefeitoRowMapper implements RowMapper<PecaDefeito>{
	
	private final Logger LOGGER = LoggerFactory.getLogger(PecaRowMapper.class);

	@Override
	public PecaDefeito mapRow(ResultSet rs, int rowNum) throws SQLException {
		LOGGER.info("Inicio PecaDefeito rowMapper");
		
		PecaDefeito pecaDefeito = new PecaDefeito();
		
		pecaDefeito.setIdPecaDefeito(rs.getInt("idPecaDefeito"));
		pecaDefeito.setFkIdDefeito(rs.getInt("fkIdDefeito"));
		pecaDefeito.setFkIdPeca(rs.getInt("fkIdPeca"));
		
		LOGGER.info("Fim PecaDefeito rowMapper");
		return pecaDefeito;
	}
	
}

package br.com.mjv.oficina.peca.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

/**
 * Classe de mapeamento do modelo {@link Peca} para a tabela TB_PECA
 * @author rogerio.pontes
 *
 */
public class PecaRowMapper implements RowMapper<Peca>{

	private final Logger LOGGER = LoggerFactory.getLogger(PecaRowMapper.class);
	
	@Override
	public Peca mapRow(ResultSet rs, int rowNum) throws SQLException {
		LOGGER.info("Inicio Peça rowMapper");
		Peca peca = new Peca();
		
		peca.setIdPeca(rs.getInt("idPeca"));
		peca.setNome(rs.getString("nome"));
		
		LOGGER.info("Fim Peça rowMapper");
		return peca;
	}
	
	
	
}

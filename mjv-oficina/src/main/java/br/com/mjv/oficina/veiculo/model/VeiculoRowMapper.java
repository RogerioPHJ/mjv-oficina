package br.com.mjv.oficina.veiculo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;


/**
 * Classe de mapeamento do modelo {@link Veiculo} para a tabela TB_VEICULO
 * @author rogerio.pontes
 *
 */
public class VeiculoRowMapper implements RowMapper<Veiculo>{

	private final Logger LOGGER = LoggerFactory.getLogger(VeiculoRowMapper.class);
	
	@Override
	public Veiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
		LOGGER.info("Inicio Veículo rowMapper");
		
		Veiculo veiculo = new Veiculo();
		
		veiculo.setIdVeiculo(rs.getInt("idVeiculo"));
		veiculo.setNome(rs.getString("nome"));
		
		LOGGER.info("Fim Veículo rowMapper");
		return veiculo;
	}

	
	
}

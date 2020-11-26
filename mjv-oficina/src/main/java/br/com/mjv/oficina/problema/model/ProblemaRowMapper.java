package br.com.mjv.oficina.problema.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * Classe de mapeamento do modelo {@link Problema} para a tabela TB_PROBLEMA
 * @author rogerio.pontes
 *
 */

public class ProblemaRowMapper implements RowMapper<Problema> {

	@Override
	public Problema mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Problema problema = new Problema();
		
		problema.setIdProblema(rs.getInt("idProblema"));
		problema.setFkIdDefeito(rs.getInt("fkIdDefeito"));
		problema.setFkIdPeca(rs.getInt("fkIdPeca"));
		problema.setFkIdVeiculo(rs.getInt("fkIdVeiculo"));
		
		return problema;
	}

}

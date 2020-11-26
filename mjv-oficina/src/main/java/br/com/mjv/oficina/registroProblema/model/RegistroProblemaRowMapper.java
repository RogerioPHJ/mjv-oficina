package br.com.mjv.oficina.registroProblema.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper; 
/**
 * Classe de mapeamento do modelo {@link RegistroProblema} para a tabela TB_REGISTRO_PROBLEMA
 * @author rogerio.pontes
 *
 */
public class RegistroProblemaRowMapper implements RowMapper<RegistroProblema> {

	@Override
	public RegistroProblema mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		RegistroProblema registroProblema = new RegistroProblema();
		
		registroProblema.setFkIdProblema(rs.getInt("fkIdProblema"));
		registroProblema.setFkIdRegistro(rs.getInt("fkIdRegistro"));
		
		return registroProblema;
	}

}

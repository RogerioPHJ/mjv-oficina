package br.com.mjv.oficina.registro.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RegistroRowMapper implements RowMapper<Registro>{

	@Override
	public Registro mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Registro registro = new Registro();
		
		registro.setData(rs.getDate("data"));
		registro.setIdRegistro(rs.getInt("idRegistro"));
		registro.setNomeCliente(rs.getString("nomeCliente"));
		registro.setNomeVeiculo(rs.getString("nomeVeiculo"));
		
		return registro;
	}

}

package br.com.mjv.oficina.registro.model;

import java.util.Date;

/**
 * Classe referente a tabela TB_REGISTRO
 * @author rogerio.pontes
 *
 */

public class Registro {
	private Integer idRegistro;
	private String nomeCliente;
	private Date data;
	private String nomeVeiculo;
	
	public Integer getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getNomeVeiculo() {
		return nomeVeiculo;
	}
	public void setNomeVeiculo(String nomeVeiculo) {
		this.nomeVeiculo = nomeVeiculo;
	}
	
}

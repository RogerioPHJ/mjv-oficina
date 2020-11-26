package br.com.mjv.oficina.problema.model;
/**
 * Classe referente a tabela TB_PROBLEMA
 * @author rogerio.pontes
 *
 */
public class Problema {
	
	private Integer idProblema;
	private Integer fkIdDefeito;
	private Integer fkIdPeca;
	private Integer fkIdVeiculo;
	
	public Integer getIdProblema() {
		return idProblema;
	}
	
	public void setIdProblema(Integer idProblema) {
		this.idProblema = idProblema;
	}
	
	public Integer getFkIdDefeito() {
		return fkIdDefeito;
	}
	
	public void setFkIdDefeito(Integer fkIdDefeito) {
		this.fkIdDefeito = fkIdDefeito;
	}
	
	public Integer getFkIdPeca() {
		return fkIdPeca;
	}
	
	public void setFkIdPeca(Integer fkIdPeca) {
		this.fkIdPeca = fkIdPeca;
	}
	
	public Integer getFkIdVeiculo() {
		return fkIdVeiculo;
	}
	
	public void setFkIdVeiculo(Integer fkIdVeiculo) {
		this.fkIdVeiculo = fkIdVeiculo;
	}
	
	
}

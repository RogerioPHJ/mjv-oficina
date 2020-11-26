package br.com.mjv.oficina.registroProblema.model;
/**
 * Classe referente a tabela TB_REGISTRO_PROBLEMA
 * @author rogerio.pontes
 *
 */
public class RegistroProblema {
	
	private Integer fkIdProblema;
	private Integer fkIdRegistro;
	
	public Integer getFkIdProblema() {
		return fkIdProblema;
	}
	public void setFkIdProblema(Integer fkIdProblema) {
		this.fkIdProblema = fkIdProblema;
	}
	public Integer getFkIdRegistro() {
		return fkIdRegistro;
	}
	public void setFkIdRegistro(Integer fkIdRegistro) {
		this.fkIdRegistro = fkIdRegistro;
	}
	
	
}

package br.com.mjv.oficina.defeito.model;

/**
 * Classe referente a tabela TB_DEFEITO
 * @author rogerio.pontes
 *
 */
public class Defeito {

	private Integer idDefeito;
	private String nome;
	
	public Integer getIdDefeito() {
		return idDefeito;
	}
	public void setIdDefeito(Integer idDefeito) {
		this.idDefeito = idDefeito;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}

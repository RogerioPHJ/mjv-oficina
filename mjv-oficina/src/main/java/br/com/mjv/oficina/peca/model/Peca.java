package br.com.mjv.oficina.peca.model;

import java.util.ArrayList;
import java.util.List;

import br.com.mjv.oficina.defeito.model.Defeito;

/**
 * Classe referente a tabela TB_PECA
 * @author rogerio.pontes
 *
 */
public class Peca {

	private Integer idPeca;
	private String nome;
	private List<Defeito> defeitos = new ArrayList<>();
	
	public Integer getIdPeca() {
		return idPeca;
	}
	public void setIdPeca(Integer idPeca) {
		this.idPeca = idPeca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Defeito> getDefeitos() {
		return defeitos;
	}
	
}

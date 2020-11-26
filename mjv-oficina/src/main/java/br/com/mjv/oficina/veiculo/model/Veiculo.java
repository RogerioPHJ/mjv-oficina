package br.com.mjv.oficina.veiculo.model;

import java.util.ArrayList;
import java.util.List;
import br.com.mjv.oficina.peca.model.Peca;

/**
 * Classe referente a tabela TB_VEICULO
 * @author rogerio.pontes
 *
 */
public class Veiculo {
	
	private Integer idVeiculo;
	private String nome;
	private List<Peca> pecas = new ArrayList<>();
	
	public Integer getIdVeiculo() {
		return idVeiculo;
	}
	public void setIdVeiculo(Integer idVeiculo) {
		this.idVeiculo = idVeiculo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public List<Peca> getPecas() {
		return pecas;
	}
}

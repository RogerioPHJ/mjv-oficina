package br.com.mjv.oficina.veiculo.dao;

import java.util.List;

import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.veiculo.model.Veiculo;

public interface VeiculoDao {

	/**
	 * Retorna uma lista de peças
	 * @return List<{@link Veiculo}>
	 */
	List<Veiculo> getAllVeiculos();
	
	/**
	 * Cadastra um novo veiculo
	 * @param veiculo
	 */
	Integer cadastrarVeiculo(Veiculo veiculo);
	
	/**
	 * Retorna uma peça de acordo com seu nome exato
	 * @return {@link Veiculo}
	 */
	Veiculo getVeiculoFirstResultByName(String name);
	
	/**
	 * Insere uma lista de peças na tabela TB_PECA_VEICULO
	 * @params list, idVeiculo 
	 */
	void linkarPecas(List<Peca> list, Integer idVeiculo);
	
	/**
	 * Cria os vinculos entre as tabelas de defeitos e peças ao veículo novo
	 * @param listPeca, idVeiculo
	 */
	void linkarProblemas(List<Peca> listPeca, Integer idVeiculo);
	
	/**
	 * Retorna um veiculo de acordo com seu id
	 * @return {@link Veiculo}
	 */
	Veiculo getById(Integer id);
}

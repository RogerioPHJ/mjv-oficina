package br.com.mjv.oficina.pecadefeito.dao;

import java.util.List;

import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;

public interface PecaDefeitoDao {

	/**
	 * Recuperar uma lista dos ids do defeitos associados as peças na tabela TB_DEFEITO_PECA
	 * @params idPeca 
	 */
	List<PecaDefeito> recuperarListDefeito(Integer idPeca);
}

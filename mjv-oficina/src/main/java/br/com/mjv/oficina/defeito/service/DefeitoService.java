package br.com.mjv.oficina.defeito.service;

import java.util.List;

import br.com.mjv.oficina.defeito.model.Defeito;
/**
 * Interface de serviços de {@link Defeitos} 
 * 
 */
public interface DefeitoService {

	/**
	 * Retorna uma lista de defeitos
	 * @return List<{@link Defeitos}>
	 */
	List<Defeito> getAllDefeitos();
	
	/**
	 * Cadastra um novo defeito
	 * @param defeito
	 */
	void cadastrarDefeito(Defeito defeito);
	
	/**
	 * Retorna um defeito de acordo com seu nome exato
	 * @return
	 */
	Defeito getDefeitoFirstResultByName(String name);
	
	/**
	 * Retorna um defeito de acordo com seu id
	 * @return {@link Defeito}
	 */
	Defeito getById(Integer id);
}

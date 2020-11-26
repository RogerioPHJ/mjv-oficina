package br.com.mjv.oficina.pecadefeito.service;

import java.util.List;


import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;


/**
 * Interface de serviços de {@link PecaDefeito} 
 * 
 */
public interface PecaDefeitoService {

	List<PecaDefeito> recuperarListDefeito(Integer idPeca);
}

package br.com.mjv.oficina.pecadefeito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mjv.oficina.pecadefeito.dao.PecaDefeitoDao;
import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;

@Service
public class PecaDefeitoServiceImpl implements PecaDefeitoService {

	@Autowired
	PecaDefeitoDao pecaDefeitoDao;
	
	@Override
	public List<PecaDefeito> recuperarListDefeito(Integer idPeca) {
		return pecaDefeitoDao.recuperarListDefeito(idPeca);
	}

}

package br.com.mjv.oficina.peca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.peca.dao.PecaDao;
import br.com.mjv.oficina.peca.model.Peca;

@Service
public class PecaServiceImpl implements PecaService {

	@Autowired
	private PecaDao pecaDao;
	
	@Override
	public List<Peca> getAllPecas() {
		return pecaDao.getAllPecas();
	}

	@Override
	public Integer cadastrarPeca(Peca peca) {
		return pecaDao.cadastrarPeca(peca);
	}

	@Override
	public Peca getPecaFirstResultByName(String name) {
		return pecaDao.getPecaFirstResultByName(name);
	}

	@Override
	public void linkarDefeitos(List<Defeito> list, Integer idPeca) {
		pecaDao.linkarDefeitos(list, idPeca);
	}

	@Override
	public Peca getById(Integer id) {
		return pecaDao.getById(id);
	}

}

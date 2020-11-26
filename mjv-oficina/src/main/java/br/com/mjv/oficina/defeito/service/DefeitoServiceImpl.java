package br.com.mjv.oficina.defeito.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mjv.oficina.defeito.dao.DefeitoDao;
import br.com.mjv.oficina.defeito.model.Defeito;

@Service
public class DefeitoServiceImpl implements DefeitoService {

	@Autowired
	private DefeitoDao defeitoDao;
	
	@Override
	public List<Defeito> getAllDefeitos() {
		List<Defeito> list = defeitoDao.getAllDefeitos();
		return list;
	}

	@Override
	public void cadastrarDefeito(Defeito defeito) {
		defeitoDao.cadastrarDefeito(defeito);
	}

	@Override
	public Defeito getDefeitoFirstResultByName(String name) {
		return defeitoDao.getDefeitoFirstResultByName(name);
	}

	@Override
	public Defeito getById(Integer id) {
		return defeitoDao.getById(id);
	}

}

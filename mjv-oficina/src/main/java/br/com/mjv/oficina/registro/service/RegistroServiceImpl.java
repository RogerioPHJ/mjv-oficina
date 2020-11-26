package br.com.mjv.oficina.registro.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mjv.oficina.problema.model.Problema;
import br.com.mjv.oficina.registro.dao.RegistroDao;
import br.com.mjv.oficina.registro.model.Registro;
import br.com.mjv.oficina.registroProblema.model.RegistroProblema;

@Service
public class RegistroServiceImpl implements RegistroService {

	@Autowired
	private RegistroDao registroDao;
	
	@Override
	public List<Registro> getAllRegistros(String name, Date dataInicio, Date dataFim) {
		return registroDao.getAllRegistros(name, dataInicio, dataFim);
	}

	@Override
	public Integer cadastrarRegistro(Registro registro, Integer[] problemas) {
		return registroDao.cadastrarRegistro(registro, problemas);
	}

	@Override
	public List<Problema> getProblemaListByVeiculoId(Integer id) {
		return registroDao.getProblemaListByVeiculoId(id);
	}

	@Override
	public void linkarProblemas(Integer idProblema, Integer idRegistro) {
		registroDao.linkarProblemas(idProblema, idRegistro);
	}

	@Override
	public Registro getById(Integer id) {
		return registroDao.getById(id);
	}

	@Override
	public List<RegistroProblema> getRegistroProblemaListByRegistroId(Integer id) {
		return registroDao.getRegistroProblemaListByRegistroId(id);
	}

	@Override
	public Problema getProblemaById(Integer id) {
		return registroDao.getProblemaById(id);
	}

	
}

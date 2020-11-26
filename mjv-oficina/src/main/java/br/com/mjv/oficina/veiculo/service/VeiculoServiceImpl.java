package br.com.mjv.oficina.veiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.veiculo.dao.VeiculoDao;
import br.com.mjv.oficina.veiculo.model.Veiculo;

@Service
public class VeiculoServiceImpl implements VeiculoService {

	@Autowired
	private VeiculoDao veiculoDao;
	
	@Override
	public List<Veiculo> getAllVeiculos() {
		return veiculoDao.getAllVeiculos();
	}

	@Override
	public Integer cadastrarVeiculo(Veiculo veiculo) {
		return veiculoDao.cadastrarVeiculo(veiculo);
	}

	@Override
	public Veiculo getVeiculoFirstResultByName(String name) {
		return veiculoDao.getVeiculoFirstResultByName(name);
	}

	@Override
	public void linkarPecas(List<Peca> list, Integer idVeiculo) {
		veiculoDao.linkarPecas(list, idVeiculo);
	}

	@Override
	public void linkarProblemas(List<Peca> listPeca, Integer idVeiculo) {
		veiculoDao.linkarProblemas(listPeca, idVeiculo);
	}

}

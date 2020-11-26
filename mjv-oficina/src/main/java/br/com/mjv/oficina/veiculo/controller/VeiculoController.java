package br.com.mjv.oficina.veiculo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.service.PecaService;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.service.VeiculoService;

/**
 * Classe controller para o cadastro de {@link Veiculo}
 * @author rogerio.pontes
 *
 */
@Controller
@RequestMapping("/veiculo")
public class VeiculoController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(VeiculoController.class);
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private VeiculoService veiculoService;

	/**
	 * Controller para a rota /veiculo
	 * @return uma página de cadastro de veículos, com o atributo pecasList vindo do return da função {@link PecaService}.getAllPecas()
	 * @routes
	 * GET /veiculo
	 */
	@GetMapping
	public String cadastrarVeiculo(Model model) {
			LOGGER.info("Início do método @Get cadastrarVeiculo");
			
			model.addAttribute("pecasList", pecaService.getAllPecas());
			
			LOGGER.info("Fim do método @Get cadastrarVeiculo");
			
			return "cadastrarveiculo";
	}
	
	/**
	 * Método para validar o cadastro de um {@link Veiculo}
	 * @param nome
	 * @param model
	 * @param pecas
	 * @return para a página cadastroconcluido caso o cadastro seja bem sucedido.
	 */
	@PostMapping("/cadastrar")
	public String salvarVeiculo(@RequestParam("peca") String[] pecas, @RequestParam("nome") String nome,Model model) {
		
		LOGGER.info("Início do método @Post salvarVeiculo");
		
		if(StringUtils.isEmpty(nome) || pecas.length == 0) {
			return "redirect:/veiculo";
		}
		
		List<String> list = Arrays.asList(pecas);
		
		List<Peca> listPeca = new ArrayList<>();
		
		Veiculo veiculo = new Veiculo();
		
		veiculo.setNome(nome);
		
		Integer idVeiculo = veiculoService.cadastrarVeiculo(veiculo);
		
		for(String name : list) {
			Peca peca = pecaService.getPecaFirstResultByName(name);
			listPeca.add(peca);
		}
		
		veiculoService.linkarPecas(listPeca, idVeiculo);
		
		veiculoService.linkarProblemas(listPeca, idVeiculo);
		
		LOGGER.info("Fim do método @Post salvarVeiculo");
		return "cadastroconcluido"; 
	}
	
	/**
	 * Método para checar se já existe o cadastro de um {@link Veiculo} no banco de dados a partir do seu nome
	 * @param name
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value="/checkname", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<Object> checkVeiculosName(@RequestParam(required = false) String name) {
		LOGGER.info("Inicio do método @Get checkVeiculosName");
		
		String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		Veiculo veiculo = veiculoService.getVeiculoFirstResultByName(cap);
		
		if(veiculo == null) {
			LOGGER.info("Fim do método @Get checkVeiculosName");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			LOGGER.info("Fim do método @Get checkVeiculosName");
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
	
}

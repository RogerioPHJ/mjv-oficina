package br.com.mjv.oficina.peca.controller;

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

import br.com.mjv.oficina.defeito.model.Defeito;
import br.com.mjv.oficina.defeito.service.DefeitoService;
import br.com.mjv.oficina.peca.model.Peca;
import br.com.mjv.oficina.peca.service.PecaService;

/**
 * Classe controller para o cadastro de {@link Peca}
 * @author rogerio.pontes
 *
 */
@Controller
@RequestMapping("/peca")
public class PecaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PecaController.class);
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private DefeitoService defeitoService;
	
	/**
	 * Controller para a rota /peca
	 * @return uma página de cadastro de peças, com o atributo defeitosList vindo do return da função {@link DefeitoService}.getAllDefeitos()
	 * @routes
	 * GET /peca
	 */
	@GetMapping
	public String cadastrarPeca(Model model) {
			LOGGER.info("Início do método @Get cadastrarPeca");
			
			model.addAttribute("defeitosList", defeitoService.getAllDefeitos());
			
			LOGGER.info("Fim do método @Get cadastrarPeca");
			
			return "cadastrarpeca";
	}
	
	/**
	 * Método para validar o cadastro de uma {@link Peca}
	 * @param nome
	 * @param model
	 * @param defeitos
	 * @return para a página cadastroconcluido caso o cadastro seja bem sucedido.
	 */
	@PostMapping("/cadastrar")
	public String salvarPeca(@RequestParam("defeito") String[] defeitos, @RequestParam("nome") String nome,Model model) {
		
		LOGGER.info("Início do método @Post salvarPeca");
		
		if(StringUtils.isEmpty(nome) || defeitos.length == 0) {
			return "redirect:/peca";
		}
		
		List<String> list = Arrays.asList(defeitos);
		
		List<Defeito> listDefeitos = new ArrayList<>();
		
		Peca peca = new Peca();
		
		peca.setNome(nome);
		
		Integer idPeca = pecaService.cadastrarPeca(peca);
		
		for(String name : list) {
			Defeito defeito = defeitoService.getDefeitoFirstResultByName(name);
			listDefeitos.add(defeito);
		}
		
		pecaService.linkarDefeitos(listDefeitos, idPeca);
		
		LOGGER.info("Início do método @Post salvarPeca");
		return "cadastroconcluido"; 
	}
	
	/**
	 * Método para checar se já existe o cadastro de uma {@link Peca} no banco de dados a partir do seu nome
	 * @param name
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value="/checkname", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<Object> checkPecasName(@RequestParam(required = false) String name) {
		LOGGER.info("Inicio do método @Get checkPecasName");
		
		String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		Peca peca = pecaService.getPecaFirstResultByName(cap);
		
		if(peca == null) {
			LOGGER.info("Fim do método @Get checkPecasName");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			LOGGER.info("Fim do método @Get checkPecasName");
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}
}

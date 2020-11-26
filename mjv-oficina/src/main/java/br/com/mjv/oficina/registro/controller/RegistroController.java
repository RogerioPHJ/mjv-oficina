package br.com.mjv.oficina.registro.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import br.com.mjv.oficina.pecadefeito.model.PecaDefeito;
import br.com.mjv.oficina.problema.model.Problema;
import br.com.mjv.oficina.registro.model.Registro;
import br.com.mjv.oficina.registro.service.RegistroService;
import br.com.mjv.oficina.registroProblema.model.RegistroProblema;
import br.com.mjv.oficina.registroTela.model.RegistroTela;
import br.com.mjv.oficina.veiculo.model.Veiculo;
import br.com.mjv.oficina.veiculo.service.VeiculoService;

/**
 * Classe controller para o cadastro de {@link Registro}
 * @author rogerio.pontes
 *
 */
@Controller
@RequestMapping("/registro")
public class RegistroController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistroController.class);
	
	@Autowired
	private RegistroService registroService;
	
	@Autowired
	private VeiculoService veiculoService;
	
	@Autowired
	private PecaService pecaService;
	
	@Autowired
	private DefeitoService defeitoService;
	
	/**
	 * Controller para a rota /registro
	 * @return uma página de cadastro de registros, com o atributo veiculosList vindo do return da função {@link VeiculoService}.getAllVeiculos()
	 * @routes
	 * GET /registro
	 */
	@GetMapping("/cadastro")
	public String cadastroRegistro(Model model) {
		LOGGER.info("Início do método @Get cadastrarRegistro");
		
		model.addAttribute("veiculosList", veiculoService.getAllVeiculos());
		
		LOGGER.info("Fim do método @Get cadastrarRegistro");
		return "cadastrarregistro";
	}
	
	/**
	 * Método Post para a rota /cadastrar
	 * @return uma página de cadastro de registros, requirindo os paramentros Problema e nomeCliente informados na tela
	 * @routes
	 * POST /cadastrar
	 */
	@PostMapping("/cadastrar")
	public String cadastrarRegistro(@RequestParam("problema") Integer[] problemas,@RequestParam("nomeCliente") String nomeCliente) { 
		try {
			Registro registro = new Registro();			
			
			LOGGER.info("Inicio do método cadastrarRegistro");
			registro.setNomeCliente(nomeCliente);
			
			Integer idRegistro = registroService.cadastrarRegistro(registro, problemas);
			
			for(Integer idProblema : problemas) {
				registroService.linkarProblemas(idProblema, idRegistro);
			}
			
			LOGGER.info("Fim do método cadastrarRegistro");
			return "cadastroconcluido";
		}catch (NullPointerException e) {
			LOGGER.error("Erro do método cadastrarRegistro: " + e.getMessage());
			return "redirect:/cadastrarregistro";
		}
	}
	/**
	 * Método para receber uma lista de peças e defeitos a partir do nome do veiculo
	 * @param id 
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value="/getpecadefeito", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<Object> getPecaDefeito(@RequestParam(required = true) Integer id) {
		LOGGER.info("Inicio do método @Get getPecaDefeito");
		
		List<RegistroProblema> listRegistroProblema = registroService.getRegistroProblemaListByRegistroId(id);
		
		if(listRegistroProblema == null) {
			LOGGER.info("Fim do método @Get getPecaDefeito");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			List<Problema> listProblema = new ArrayList<>();
			
			for(RegistroProblema registroProblema : listRegistroProblema) {
				listProblema.add(registroService.getProblemaById(registroProblema.getFkIdProblema()));
			}
			
			List<PecaDefeito> listPecaDefeito = new ArrayList<>();		
			
			for(Problema problema : listProblema) {
				PecaDefeito pecaDefeito = new PecaDefeito();
				Peca peca = pecaService.getById(problema.getFkIdPeca());
				pecaDefeito.setNomePeca(peca.getNome());
				Defeito defeito = defeitoService.getById(problema.getFkIdDefeito());
				pecaDefeito.setNomeDefeito(defeito.getNome());
				
				listPecaDefeito.add(pecaDefeito);
			}
			
			return new ResponseEntity<Object>(listPecaDefeito, HttpStatus.OK);
		}
	}
	
	/**
	 * Método para receber uma lista de problemas a partir do nome do veiculo
	 * @param name
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value="/getproblemas", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<Object> getProblemas(@RequestParam(required = true) String name) {
		LOGGER.info("Inicio do método @Get getProblemas");
		
		String cap = name.substring(0, 1).toUpperCase() + name.substring(1);
		
		Veiculo veiculo = veiculoService.getVeiculoFirstResultByName(cap);
		
		if(veiculo == null) {
			LOGGER.info("Fim do método @Get getProblemas");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			List<Problema> listProblema = registroService.getProblemaListByVeiculoId(veiculo.getIdVeiculo());
			
			List<RegistroTela> list = new ArrayList<>();
			
			for(Problema item : listProblema) {
				Defeito defeito = defeitoService.getById(item.getFkIdDefeito()); 
				Peca peca = pecaService.getById(item.getFkIdPeca());
				
				RegistroTela registroTela = new RegistroTela();
				
				registroTela.setDefeito(defeito.getNome());
				registroTela.setPeca(peca.getNome());
				registroTela.setFkIdProblema(item.getIdProblema());
				
				list.add(registroTela);
			}
			
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		}
	}
	
	/**
	 * Método para consultar registro baseado no model
	 * @param model
	 * @routes
	 * GET /consulta
	 */
	@GetMapping("/consulta")
	public String consultarRegistro(Model model) {
		LOGGER.info("Início do método @Get consultarRegistro");
		
		model.addAttribute("veiculosList", veiculoService.getAllVeiculos());
		model.addAttribute("registrosList", registroService.getAllRegistros(null, null, null));
		
		LOGGER.info("Fim do método @Get consultarRegistro");
		return "consultarregistro";
	}
	
	/**
	 * Método para receber uma lista de registros a partir de informações do veiculo e datas 
	 * @param nameVeiculo, dataInicio, dataFim
	 * @return {@link ResponseEntity}
	 */
	@RequestMapping(value="/consultar", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public ResponseEntity<Object> getRegistros(@RequestParam(required = false) String nameVeiculo, @RequestParam(required = false) String dataInicio, @RequestParam(required = false) String dataFim) {
		LOGGER.info("Início do método @GET getRegistros");
		
		Date dtInicio = null;
		Date dtFim = null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		
		try {
			if(!StringUtils.isEmpty(dataInicio)) {
				dataInicio = dataInicio.substring(8, 10) + "/" + dataInicio.substring(5, 7) + "/" + dataInicio.substring(0, 4);
				dtInicio = sdf.parse(dataInicio + " 00:00:00");
			}
			
			if(!StringUtils.isEmpty(dataFim)) {
				dataFim = dataFim.substring(8, 10) + "/" + dataFim.substring(5, 7) + "/" + dataFim.substring(0, 4);
				dtFim = sdf.parse(dataFim + " 23:59:59");
			}
		}catch(Exception e) {
			LOGGER.error("A data foi informada fora do padrão.");
		}

		List<Registro> list = registroService.getAllRegistros(nameVeiculo, dtInicio, dtFim);
		
		LOGGER.info("Fim do método @GET getRegistros");
		return new ResponseEntity<Object>(list, HttpStatus.OK);
	}
	
}

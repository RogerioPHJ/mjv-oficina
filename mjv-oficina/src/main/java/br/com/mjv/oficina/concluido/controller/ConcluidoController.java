package br.com.mjv.oficina.concluido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Classe controller para a tela de concluido
 * @author rogerio.pontes
 *
 */
@Controller
@RequestMapping("/concluido")
public class ConcluidoController {
	
	
	@GetMapping
	public String iniciarDefeito() {
		return "cadastroconcluido";
	}
	
}
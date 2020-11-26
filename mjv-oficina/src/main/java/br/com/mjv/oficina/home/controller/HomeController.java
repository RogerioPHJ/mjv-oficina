package br.com.mjv.oficina.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Classe controller para a rota inicial (home)
 * @author rogerio.pontes
 *
 */
@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = RequestMethod.GET)
	public String iniciarHome() {
		return "home";
	}
	
}

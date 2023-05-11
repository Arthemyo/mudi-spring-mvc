package br.com.alura.mvc.mudi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
		
	@GetMapping("/login")
	public String login() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if(!(authentication.getName() == "anonymousUser")) {
			return "redirect:/usuario/pedido";
		}
	
		return "login";
	}
}

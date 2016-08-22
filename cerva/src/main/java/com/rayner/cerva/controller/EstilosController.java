package com.rayner.cerva.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rayner.cerva.model.Estilo;
import com.rayner.cerva.service.EstiloService;
import com.rayner.cerva.service.exception.NomeEstiloJaCadastradoException;

@Controller
@RequestMapping(value = "/estilos")
public class EstilosController {

	@Autowired
	private EstiloService estiloService;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public String novoEstilo(Estilo estilo) {
		return "estilo/CadastroEstilo";
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Valid Estilo estilo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novoEstilo(estilo);
		}

		try {
			estiloService.salvar(estilo);
		} catch (NomeEstiloJaCadastradoException excecao) {
			result.rejectValue("nome", excecao.getMessage(), excecao.getMessage());
			return novoEstilo(estilo);//se vir para cá, ele envia o objeto estilo de novo para o GET
		}
		attributes.addFlashAttribute("mensagem", "Estilo salvo com sucesso");
		return "redirect:/estilos/novo";
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody  @Valid Estilo estilo, BindingResult result){
		if(result.hasErrors()){
			return ResponseEntity.badRequest().body(result.getFieldError("nome").getDefaultMessage());
		}
		
		//try{
			estilo = estiloService.salvar(estilo);
	/*	} catch(NomeEstiloJaCadastradoException exception){
			return ResponseEntity.badRequest().body(exception.getMessage());
		}*/
		
		return ResponseEntity.ok(estilo);		
	}

}

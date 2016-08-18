package com.rayner.cerva.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rayner.cerva.model.Estilo;
import com.rayner.cerva.service.EstiloService;
import com.rayner.cerva.service.exception.NomeEstiloJaCadastradoException;

@Controller
public class EstilosController {

	@Autowired
	private EstiloService estiloService;

	@RequestMapping(value = "/estilos/novo", method = RequestMethod.GET)
	public String novoEstilo(Estilo estilo) {
		return "estilo/CadastroEstilo";
	}

	@RequestMapping(value = "/estilos/novo", method = RequestMethod.POST)
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

}

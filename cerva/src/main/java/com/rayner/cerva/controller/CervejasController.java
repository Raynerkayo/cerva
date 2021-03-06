package com.rayner.cerva.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rayner.cerva.model.Cerveja;
import com.rayner.cerva.model.Origem;
import com.rayner.cerva.model.Sabor;
import com.rayner.cerva.repository.EstiloRepository;
import com.rayner.cerva.service.CervejaService;
import com.rayner.cerva.service.exception.SkuJaExistente;

@Controller
public class CervejasController {

	@Autowired
	private EstiloRepository estilosRepository;

	@Autowired
	private CervejaService cervejaService;

	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja, Model model) {
		model.addAttribute("sabores", Sabor.values());
		model.addAttribute("estilos", estilosRepository.findAll());
		model.addAttribute("origens", Origem.values());
		return "cerveja/CadastroCerveja";
	}

	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return novo(cerveja, model);
		}
		try {
			cervejaService.salvar(cerveja);
		} catch (SkuJaExistente skuJaExistente) {
			result.rejectValue("sku", skuJaExistente.getMessage(), skuJaExistente.getMessage());
			return novo(cerveja, model);
		}
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		return "redirect:/cervejas/novo";
	}
}

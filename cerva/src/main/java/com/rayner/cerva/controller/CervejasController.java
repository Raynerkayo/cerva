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

@Controller
public class CervejasController {
	
	@Autowired
	private EstiloRepository estilosRepository;	
	
	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja, Model model) {//já passo o objeto aqui para ser usado no thymeleaf, na validação
		model.addAttribute("sabores", Sabor.values());
		model.addAttribute("estilos", estilosRepository.findAll());
		model.addAttribute("origens", Origem.values());
		return "cerveja/CadastroCerveja";
	}
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		/*if (result.hasErrors()) {
			return novo(cerveja, model);//se houver erro, eu passo o mesmo objeto cerveja para get novo
		}*/
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso!");
		System.out.println(">>> sku: " + cerveja.getSku());
		System.out.println("Sabor >>: " + cerveja.getSabor());
		System.out.println("Origem >>:" + cerveja.getOrigem());
		System.out.println("Estilo >>:" + cerveja.getEstilo());
		return "redirect:/cervejas/novo";
	}
	
 }

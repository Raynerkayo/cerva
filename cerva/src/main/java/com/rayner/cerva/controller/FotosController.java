package com.rayner.cerva.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.rayner.cerva.dto.FotoDTO;
import com.rayner.cerva.storage.FotoStorage;
import com.rayner.cerva.storage.FotoStorageRunnable;

@RestController
@RequestMapping(value = "/fotos")
public class FotosController {

	@Autowired
	private FotoStorage fotoStorage;
	
	//DeferredResult é para postergar o resultado. 
	@PostMapping
	public DeferredResult<FotoDTO> upload(@RequestParam("files[]") MultipartFile[] files){
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		
		//para fazer o retorno assícrono
		Thread thread = new Thread(new FotoStorageRunnable(files, resultado, fotoStorage));
		thread.start();
		
		return resultado;
	}
	
	/**
	 * nome:.* expressão regular, que permite qq coisa após o nome
	 * @param nome
	 * @return o método recuperarFotoTemporario(nome).
	 */
	@GetMapping(value = "/temp/{nome:.*}")
	public byte[] recuperarFotoTemporario(@PathVariable String nome){
		return fotoStorage.recuperaFotoTemporario(nome);
	}
	
	
	/* antes de usar threads
	//@RequestMapping(method = RequestMethod.POST) funciona para tds as versões
	@PostMapping//apenas veroa 4.3 ou superior do Spring. Substitui @RequestMapping
	public String upload(@RequestParam("files[]") MultipartFile[] files){
		System.out.println("Tamanho do arquivo :" + files[0].getSize());
		return "Ok";
	}
	*/
}

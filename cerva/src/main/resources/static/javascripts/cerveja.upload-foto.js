var Cerva = Cerva || {};

Cerva.UploadFoto = (function(){
	
	//Construtor
	function UploadFoto(){
		this.inputNomeFoto = $('input[name=foto]');
		this.inputContentType = $('input[name=contentType]');
		
		this.htmlDaFotoCervejaTemplate = $('#foto-cerveja').html();
		this.template = Handlebars.compile(this.htmlDaFotoCervejaTemplate);

		this.containerFotoCerveja = $('.js-container-foto-cerveja');
		
		this.uploadDrop = $('#upload-drop');
	}

	//Função de inicialização
	UploadFoto.prototype.iniciar = function(){
		var settings = {
			type: 'json',
			filelimit: 1,
			allow: '*.(jpg|jpeg|png|pdf)',
			action: this.containerFotoCerveja.data('url-fotos'),
			complete: onUploadCompleto.bind(this)
		}
		
		UIkit.uploadSelect($('#upload-select'), settings);
		UIkit.uploadDrop(this.uploadDrop, settings);
		
		if(this.inputNomeFoto.val()){
			onUploadCompleto.call(this, { nome: this.inputNomeFoto.val(), contentType: this.inputContentType.val()});
		}
		
	}
	
	function onUploadCompleto(resposta){
		this.inputNomeFoto.val(resposta.nome);
		this.inputContentType.val(resposta.contentType);
		
		this.uploadDrop.addClass('hidden');
		
		var htmlFotoCerveja = this.template({nomeFoto: resposta.nome});
		
		this.containerFotoCerveja.append(htmlFotoCerveja);
		
		$('.js-remove-foto').on('click', onRemoverFoto.bind(this));
	}
	
	function onRemoverFoto(){
		$('.js-foto-cerveja').remove();
		this.uploadDrop.removeClass('hidden');
		this.inputNomeFoto.val('');
		this.inputContentType.val('');
	}
	
	return UploadFoto;
	
})();

$(function (){
	
	var uploadFoto = new Cerva.UploadFoto();
	uploadFoto.iniciar();
	
});
package com.algafood.api.exceptionhandler;

public enum ProblemType {

	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	MENSAGEM_IMCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public String getUri() {
		return uri;
	}
	
}

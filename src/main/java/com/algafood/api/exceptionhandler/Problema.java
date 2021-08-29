package com.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

public class Problema {
	
	private LocalDateTime dataHora;
	private String mensagem;
	
	public Problema(String mensagem, LocalDateTime dataHora) {
		this.mensagem = mensagem;
		this.dataHora = dataHora;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public String getMensagem() {
		return mensagem;
	}

}

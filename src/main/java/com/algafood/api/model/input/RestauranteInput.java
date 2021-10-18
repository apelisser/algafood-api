package com.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.algafood.core.validation.TaxaFrete;

public class RestauranteInput {
	
	@NotBlank
	private String nome;
	
	@NotNull
	@TaxaFrete
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInput cozinha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}
	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}
	public CozinhaIdInput getCozinha() {
		return cozinha;
	}
	public void setCozinha(CozinhaIdInput cozinha) {
		this.cozinha = cozinha;
	}
	
}

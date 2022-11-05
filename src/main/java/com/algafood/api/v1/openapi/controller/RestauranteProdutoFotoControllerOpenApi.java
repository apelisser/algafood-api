package com.algafood.api.v1.openapi.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.multipart.MultipartFile;

import com.algafood.api.v1.model.FotoProdutoModel;
import com.algafood.api.v1.model.input.FotoProdutoInput;

public interface RestauranteProdutoFotoControllerOpenApi {

	public FotoProdutoModel atualizarFoto(Long restauranteId, Long produtoId, FotoProdutoInput fotoProdutoInput,
			MultipartFile arquivo) throws IOException;

	public FotoProdutoModel buscar(Long restauranteId, Long produtoId);

	public ResponseEntity<?> servir(Long restauranteId, Long produtoId, String acceptHeader)
			throws HttpMediaTypeNotAcceptableException;

	public void excluir(Long restauranteId, Long produtoId);

}

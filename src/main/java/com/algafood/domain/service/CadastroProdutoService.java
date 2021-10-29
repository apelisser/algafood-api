package com.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algafood.domain.model.Produto;
import com.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

//	private static final String MSG_PRODUTO_EM_USO = "Produto de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@Transactional
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId)
				.orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId));
	}
	
}

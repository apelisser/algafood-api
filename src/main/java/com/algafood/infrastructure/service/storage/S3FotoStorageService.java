package com.algafood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;

@Service
public class S3FotoStorageService implements FotoStorageService {


	@Override
	public void armazenar(NovaFoto novaFoto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remover(String nomeArquivo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

}

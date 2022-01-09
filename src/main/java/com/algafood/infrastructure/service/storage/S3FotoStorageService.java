package com.algafood.infrastructure.service.storage;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algafood.core.storage.StorageProperties;
import com.algafood.domain.service.FotoStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3FotoStorageService implements FotoStorageService {

	@Autowired
	private AmazonS3 amazonS3;

	@Autowired
	private StorageProperties storageProperties;

	@Override
	public void armazenar(NovaFoto novaFoto) {
		try {
			String caminhoArquivo = getCaminhoArquivo(novaFoto.getNomeArquivo());

			var objectMetadata = new ObjectMetadata();
			objectMetadata.setContentType(novaFoto.getContetType());
			objectMetadata.setContentLength(novaFoto.getContentLength());

			var putObjectRequest = new PutObjectRequest(
					storageProperties.getS3().getBucket(), 
					caminhoArquivo,
					novaFoto.getInputStream(), 
					objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead);

			amazonS3.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível enviar arquivo para Amazon-S3.", e);
		}
	}

	@Override
	public void remover(String nomeArquivo) {
		try {
			String caminhoArquivo = getCaminhoArquivo(nomeArquivo);
			
			var deleteObjectRequest = new DeleteObjectRequest(
					storageProperties.getS3().getBucket(), 
					caminhoArquivo);
			
			amazonS3.deleteObject(deleteObjectRequest);
		} catch (Exception e) {
			throw new StorageException("Não foi possível excluir arquivo na Amazon S3.", e);
		}
	}

	@Override
	public InputStream recuperar(String nomeArquivo) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getCaminhoArquivo(String nomeArquivo) {
		return String.format("%s/%s", storageProperties.getS3().getDiretorioFotos(), nomeArquivo);
	}

}
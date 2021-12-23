package com.algafood.core.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class FileContentTypeValidator implements ConstraintValidator<FileContentType, MultipartFile> {

	private List<String> allowedContentTypes;
	
	@Override
	public void initialize(FileContentType constraintAnnotation) {
		this.allowedContentTypes = Arrays.asList(constraintAnnotation.allowed());
	}
	
	@Override
	public boolean isValid(MultipartFile multiPartFile, ConstraintValidatorContext context) {
		return multiPartFile == null 
				|| this.allowedContentTypes.contains(multiPartFile.getContentType());
	}

}

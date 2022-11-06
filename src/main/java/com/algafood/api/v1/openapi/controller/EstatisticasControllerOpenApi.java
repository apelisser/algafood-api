package com.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "security_auth")
public interface EstatisticasControllerOpenApi {

	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset);

	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

	public EstatisticasModel estatisticas();
	
}

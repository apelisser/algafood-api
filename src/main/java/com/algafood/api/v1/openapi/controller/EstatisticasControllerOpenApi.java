package com.algafood.api.v1.openapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.algafood.api.v1.controller.EstatisticasController.EstatisticasModel;
import com.algafood.domain.filter.VendaDiariaFilter;
import com.algafood.domain.model.dto.VendaDiaria;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@SecurityRequirement(name = "security_auth")
@Tag(name = "Estatísticas")
public interface EstatisticasControllerOpenApi {

	@Operation(
			summary = "Consulta estatísticas de vendas diárias",
			parameters = {
					@Parameter(in = ParameterIn.QUERY, name = "restauranteId", description = "ID do restaurante", 
							example = "1", schema = @Schema(type = "integer")),
					@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoInicio", description = "Data/hora inicial da criação do pedido", 
							example = "2022-12-01T00:00:00Z", schema = @Schema(type = "string", format = "date-time")),
					@Parameter(in = ParameterIn.QUERY, name = "dataCriacaoFim", description = "Data/hora final da criação do pedido", 
							example = "2022-12-04T23:59:59Z", schema = @Schema(type = "string", format = "date-time"))
			},
			responses = {
					@ApiResponse(responseCode = "200", content = {
							@Content(mediaType = "aplication/json", array = @ArraySchema(schema = @Schema(implementation = VendaDiaria.class))),
							@Content(mediaType = "application/pdf", schema = @Schema(type = "string", format = "binary"))
					})
			}
	)
	public List<VendaDiaria> consultarVendasDiarias(
			@Parameter(hidden = true)
			VendaDiariaFilter filtro, 
			@Parameter(description = "Deslocamento de horário a ser considerado na consulta em relação ao UTC", 
				schema = @Schema(type = "string", defaultValue = "+00:00"))
			String timeOffset);

	@Operation(hidden = true)
	public ResponseEntity<byte[]> consultarVendasDiariasPdf(VendaDiariaFilter filtro, String timeOffset);

	@Operation(hidden = true)
	public EstatisticasModel estatisticas();
	
}

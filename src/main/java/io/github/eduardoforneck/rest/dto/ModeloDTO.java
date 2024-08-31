package io.github.eduardoforneck.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModeloDTO {

    @NotNull(message = "{informe_o_id_da_marca}")
    private Integer marca_id;
	
	@NotBlank(message = "{informe_o_nome_do_modelo}")
    private String nome;
	
	@NotNull(message = "{informe_o_valor_da_fipe_do_modelo}")
    private BigDecimal valor_fipe;
}

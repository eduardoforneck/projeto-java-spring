package io.github.eduardoforneck.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarroDTO {

    @NotNull(message = "{informe_o_id_do_modelo}")
    private Integer modelo_id;
	
	@NotNull(message = "{informe_o_ano_do_carro}")
    private Integer ano;
	
	@NotBlank(message = "{informe_o_combustivel_do_carro}")
    private String combustivel;
	
	@NotNull(message = "{informe_o_numero_de_portas_do_carro}")
    private Integer num_portas;
	
	@NotBlank(message = "{informe_a_cor_do_carro}")
    private String cor;
}

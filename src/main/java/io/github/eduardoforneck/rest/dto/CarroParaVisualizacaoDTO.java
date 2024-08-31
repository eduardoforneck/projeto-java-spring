package io.github.eduardoforneck.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarroParaVisualizacaoDTO {

    private Integer id;
    private Long timestamp_cadastro;
    private Integer modelo_id;
    private Integer ano;
    private String combustivel;
    private Integer num_portas;
    private String cor;
    private String nome_modelo;
    private BigDecimal valor;

}

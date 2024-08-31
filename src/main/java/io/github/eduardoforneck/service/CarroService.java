package io.github.eduardoforneck.service;

import io.github.eduardoforneck.domain.entity.Carro;
import io.github.eduardoforneck.rest.dto.CarroDTO;
import io.github.eduardoforneck.rest.dto.CarroParaVisualizacaoDTO;

import java.util.List;

public interface CarroService {
    Integer salvar(CarroDTO dto);
    List<CarroParaVisualizacaoDTO> listarCarrosParaVisualizacao(Carro filtro);
}

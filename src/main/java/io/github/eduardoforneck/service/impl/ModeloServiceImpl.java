package io.github.eduardoforneck.service.impl;

import io.github.eduardoforneck.domain.entity.Marca;
import io.github.eduardoforneck.domain.entity.Modelo;
import io.github.eduardoforneck.domain.repository.Marcas;
import io.github.eduardoforneck.domain.repository.Modelos;
import io.github.eduardoforneck.exception.RegraNegocioException;
import io.github.eduardoforneck.rest.dto.ModeloDTO;
import io.github.eduardoforneck.service.ModeloService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ModeloServiceImpl implements ModeloService {

    private final Marcas marcas;
    private final Modelos modelos;

    @Override
    @Transactional
    public Integer salvar(ModeloDTO dto) {

        Integer marcaId = dto.getMarca_id();

        Marca marca = marcas
                .findById(marcaId)
                .orElseThrow(() -> new RegraNegocioException("ID da marca inválido."));

        Modelo modelo = new Modelo();
        modelo.setMarca(marca);
        modelo.setNome(dto.getNome());
        modelo.setValor_fipe(dto.getValor_fipe());

        modelos.save(modelo);

        return modelo.getId();
    }
}

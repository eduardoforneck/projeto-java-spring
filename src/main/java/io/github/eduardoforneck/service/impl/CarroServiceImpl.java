package io.github.eduardoforneck.service.impl;

import io.github.eduardoforneck.domain.entity.Carro;
import io.github.eduardoforneck.domain.entity.Modelo;
import io.github.eduardoforneck.domain.enums.Combustivel;
import io.github.eduardoforneck.domain.repository.Carros;
import io.github.eduardoforneck.domain.repository.Modelos;
import io.github.eduardoforneck.exception.RegraNegocioException;
import io.github.eduardoforneck.rest.dto.CarroDTO;
import io.github.eduardoforneck.rest.dto.CarroParaVisualizacaoDTO;
import io.github.eduardoforneck.service.CarroService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarroServiceImpl implements CarroService {

    private final Carros carros;
    private final Modelos modelos;

    @Override
    @Transactional
    public Integer salvar(CarroDTO dto) {

        Modelo modelo = encontrarModelo(dto.getModelo_id());
        String combustivelTratado = dto.getCombustivel().toUpperCase().trim();

        Carro carro = new Carro();
        carro.setModelo(modelo);
        carro.setTimestamp_cadastro(LocalDateTime.now());
        carro.setAno(dto.getAno());
		carro.setCor(dto.getCor().toUpperCase());
        carro.setNum_portas(dto.getNum_portas());
		carro.setCombustivel(Combustivel.valueOf(combustivelTratado));

        carros.save(carro);

        return carro.getId();
    }

    @Override
    public List<CarroParaVisualizacaoDTO> listarCarrosParaVisualizacao(Carro filtro) {

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        List<Carro> listaCarros = carros.findAll(Example.of(filtro, matcher));

        return listaCarros.stream().map(car -> {

            Modelo modelo = encontrarModelo(car.getModelo().getId());

            return CarroParaVisualizacaoDTO
                    .builder()
                    .id(car.getId())
                    .ano(car.getAno())
                    .cor(car.getCor())
                    .combustivel(car.getCombustivel().name())
                    .num_portas(car.getNum_portas())
                    .timestamp_cadastro(car.getTimestamp_cadastro().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli())
                    .valor(modelo.getValor_fipe())
                    .modelo_id(modelo.getId())
                    .nome_modelo(modelo.getNome())
                    .build();

        }).collect(Collectors.toList());

    }

    private Modelo encontrarModelo(Integer id){
        return modelos
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException("ID do modelo inválido."));
    }
}

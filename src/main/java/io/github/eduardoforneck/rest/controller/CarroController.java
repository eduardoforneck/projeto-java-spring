package io.github.eduardoforneck.rest.controller;

import io.github.eduardoforneck.domain.entity.Carro;
import io.github.eduardoforneck.domain.repository.Carros;
import io.github.eduardoforneck.rest.dto.CarroDTO;
import io.github.eduardoforneck.rest.dto.CarroParaVisualizacaoDTO;
import io.github.eduardoforneck.rest.dto.CarroResponse;
import io.github.eduardoforneck.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController {

    private Carros carros;
    private CarroService carroService;

    public CarroController(Carros carros, CarroService carroService) {
        this.carros = carros;
        this.carroService = carroService;
    }

    @GetMapping("{id}")
    public Carro getCarroById(@PathVariable Integer id ){
        Carro carro = carros
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));
        return carro;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid CarroDTO dto){
        return carroService.salvar(dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        carros
                .findById(id)
                .map(c -> {
                    carros.delete(c);
                    return c;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Carro carro){
        carros
                .findById(id)
                .map(c -> {
                    carro.setId(c.getId());
                    carros.save(carro);
                    return c;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carro não encontrado"));

    }

    @GetMapping
    public List<Carro> find(Carro filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return carros.findAll(Example.of(filtro, matcher));
    }

    @GetMapping("/listarParaVisualizacao")
    public CarroResponse listarParaVisualizacao(Carro filtro){
        return CarroResponse
                .builder()
                .cars(carroService.listarCarrosParaVisualizacao(filtro))
                .build();
    }

}

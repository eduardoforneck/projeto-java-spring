package io.github.eduardoforneck.rest.controller;

import io.github.eduardoforneck.domain.entity.Modelo;
import io.github.eduardoforneck.domain.repository.Modelos;
import io.github.eduardoforneck.rest.dto.ModeloDTO;
import io.github.eduardoforneck.service.ModeloService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
public class ModeloController {

    private Modelos modelos;
    private ModeloService modeloService;

    public ModeloController(Modelos modelos, ModeloService modeloService) {
        this.modelos = modelos;
        this.modeloService = modeloService;
    }

    @GetMapping("{id}")
    public Modelo getModeloById(@PathVariable Integer id ){
        return modelos
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid ModeloDTO dto){ return modeloService.salvar(dto); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        modelos
                .findById(id)
                .map(m -> {
                    modelos.delete(m);
                    return m;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Modelo modelo){
        modelos
                .findById(id)
                .map(m -> {
                    modelo.setId(m.getId());
                    modelos.save(modelo);
                    return m;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Modelo não encontrado"));

    }

    @GetMapping
    public List<Modelo> find(Modelo filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return modelos.findAll(Example.of(filtro, matcher));
    }

}

package io.github.eduardoforneck.rest.controller;

import io.github.eduardoforneck.domain.entity.Marca;
import io.github.eduardoforneck.domain.repository.Marcas;
import jakarta.validation.Valid;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    private Marcas marcas;

    public MarcaController(Marcas marcas) {
        this.marcas = marcas;
    }

    @GetMapping("{id}")
    public Marca getMarcaById(@PathVariable Integer id ){
        return marcas
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Marca save(@RequestBody @Valid Marca marca){
        return marcas.save(marca);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        marcas
                .findById(id)
                .map(m -> {
                    marcas.delete(m);
                    return m;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody @Valid Marca marca){
        marcas
                .findById(id)
                .map(m -> {
                    marca.setId(m.getId());
                    marcas.save(marca);
                    return m;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Marca não encontrada"));

    }

    @GetMapping
    public List<Marca> find(Marca filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return marcas.findAll(Example.of(filtro, matcher));
    }

}

package io.github.eduardoforneck.domain.repository;

import io.github.eduardoforneck.domain.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Carros extends JpaRepository<Carro, Integer> {}

package io.github.eduardoforneck.domain.repository;

import io.github.eduardoforneck.domain.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Marcas extends JpaRepository<Marca, Integer> {}

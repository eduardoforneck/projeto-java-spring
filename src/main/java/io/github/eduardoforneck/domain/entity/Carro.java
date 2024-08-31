package io.github.eduardoforneck.domain.entity;

import io.github.eduardoforneck.domain.enums.Combustivel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carro")
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	@Column(name = "timestamp_cadastro")
    private LocalDateTime timestamp_cadastro;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;
	
	@Column
    private Integer ano;
	
	@Enumerated(EnumType.STRING)
    @Column(name = "combustivel")
    private Combustivel combustivel;

    @Column
    private Integer num_portas;
	
	@Column
    private String cor;
}

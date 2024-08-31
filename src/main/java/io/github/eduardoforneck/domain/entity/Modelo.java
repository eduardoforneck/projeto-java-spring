package io.github.eduardoforneck.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;
	
	@Column
    private String nome;

    @Column(name = "valor_fipe", precision = 20, scale = 2)
    private BigDecimal valor_fipe;

    @JsonIgnore
	@OneToMany(mappedBy = "modelo", fetch = FetchType.LAZY)
    private List<Carro> carros;
}

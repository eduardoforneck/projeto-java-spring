package io.github.eduardoforneck.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "marca")
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome_marca")
    @NotBlank(message = "{informe_o_nome_da_marca}")
    private String nomeMarca;

    @JsonIgnore
	@OneToMany(mappedBy = "marca", fetch = FetchType.LAZY)
    private List<Modelo> modelos;
}

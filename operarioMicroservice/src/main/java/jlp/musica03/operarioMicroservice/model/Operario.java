package jlp.musica03.operarioMicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="operario")
@Table(name="opetarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operario {

    @Id
    private String rut;

    @Column(name = "nombre", length = 50, nullable = false)
    private String nombre;

    @Column(name = "telefono", nullable = true)
    private Integer telefono;

}
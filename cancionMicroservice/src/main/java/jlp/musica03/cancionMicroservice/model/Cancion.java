package jlp.musica03.cancionMicroservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="cancion")
@Table(name="canciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 250, nullable = false)
    private String nombre;

    @Column(name = "duracion_segundos", nullable = false)
    private Integer duracionSegundos;

    @Column(name = "letra", length = 3000, nullable = true)
    private String letra;

    @Column(name = "genero_id", nullable = false)
    private Long generoId;
}

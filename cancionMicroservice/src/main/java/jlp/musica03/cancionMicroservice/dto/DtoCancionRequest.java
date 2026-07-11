package jlp.musica03.cancionMicroservice.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

public class DtoCancionRequest {

    @NotNull(message = "Nombre es obligatorio")
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotNull(message = "Tiempo en segundos es obligatorio")
    @Positive(message = "La duración es un número positivo")
    private Integer duracionSegundos;

    private String letra;

    @NotNull(message = "El Id de género es obligatorio")
    @Positive(message = "El Id de género es un número positivo")
    private Long generoId;

    public DtoCancionRequest() {}

    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}

    public Integer getDuracionSegundos() {return duracionSegundos;}
    public void setDuracionSegundos(Integer duracionSegundos) {this.duracionSegundos = duracionSegundos;}

    public String getLetra() {return letra;}
    public void setLetra(String letra) {this.letra = letra;}

    public Long getGeneroId() {return generoId;}
    public void setGeneroId(Long generoId) {this.generoId = generoId;}
}

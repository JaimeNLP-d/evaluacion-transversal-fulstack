package jlp.musica03.grupoMicroservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoGrupoRequest {

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;
}

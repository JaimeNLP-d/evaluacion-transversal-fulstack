package jlp.musica03.operarioMicroservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DtoOperarioRequest {

    @NotBlank(message = "RUT es obligatorio")
    private String rut;

    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    @NotNull(message = "Telefono es obligatorio")
    @Size(max=9, min=9, message="Numero debe ser de largo 9")
    private Integer telefono;

}

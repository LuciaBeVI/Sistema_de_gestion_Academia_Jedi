package com.academiajedi.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class MaestroDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    private String nombre;

    @NotBlank(message = "La especialidad es obligatoria")
    @Size(min = 2, max = 50)
    private String especialidad;
}
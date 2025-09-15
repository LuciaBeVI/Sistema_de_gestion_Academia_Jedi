package com.academiajedi.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PadawanDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50)
    private String nombre;

    @NotBlank(message = "El rango es obligatorio")
    private String rango;

    @Min(value = 10, message = "La edad mínima es 10")
    private Integer edad;
}
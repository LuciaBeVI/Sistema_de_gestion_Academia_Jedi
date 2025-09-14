package com.academiajedi.entity;

import lombok.*;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Maestro {

    private Long id;
    private String nombre;
    private String especialidad;

    @Builder.Default
    private List<Padawan> padawans = List.of();
}
package com.academiajedi.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Padawan {
    private Long id;
    private String nombre;
    private String rango;
    private int edad;
}
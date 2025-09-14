package com.academiajedi.factory;

import com.academiajedi.entity.Padawan;

public class PadawanFactory {

    public static Padawan createPadawan(Long id, String nombre, String rango, int edad) {
        return Padawan.builder()
                .id(id)
                .nombre(nombre)
                .rango(rango)
                .edad(edad)
                .build();
    }
}
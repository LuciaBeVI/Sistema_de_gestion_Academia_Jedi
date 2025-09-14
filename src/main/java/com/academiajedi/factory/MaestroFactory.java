package com.academiajedi.factory;

import com.academiajedi.entity.Maestro;
import java.util.ArrayList;

public class MaestroFactory {

    public static Maestro createMaestro(Long id, String nombre, String especialidad) {
        return Maestro.builder()
                .id(id)
                .nombre(nombre)
                .especialidad(especialidad)
                .padawans(new ArrayList<>())
                .build();
    }
}
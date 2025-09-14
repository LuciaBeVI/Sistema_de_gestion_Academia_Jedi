package com.academiajedi.repository;

import com.academiajedi.entity.Maestro;
import com.academiajedi.entity.Padawan;

import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;
import java.util.*;

@Component
public class InMemoryDatabase {
    public List<Padawan> padawans = new ArrayList<>();
    public List<Maestro> maestros = new ArrayList<>();

    @PostConstruct
    public void init() {

        Padawan padawan1 = Padawan.builder()
                .id(1L)
                .nombre("Luke Skywalker")
                .rango("Novato")
                .edad(19)
                .build();

        Padawan padawan2 = Padawan.builder()
                .id(2L)
                .nombre("Rey")
                .rango("Aprendiz")
                .edad(21)
                .build();

        Padawan padawan3 = Padawan.builder()
                .id(3L)
                .nombre("Ahsoka Tano")
                .rango("Avanzado")
                .edad(22)
                .build();

        padawans.addAll(Arrays.asList(padawan1, padawan2, padawan3));


        Maestro maestro1 = Maestro.builder()
                .id(1L)
                .nombre("Yoda")
                .especialidad("Fuerza")
                .padawans(new ArrayList<>())
                .build();

        Maestro maestro2 = Maestro.builder()
                .id(2L)
                .nombre("Obi-Wan Kenobi")
                .especialidad("Sable de luz")
                .padawans(new ArrayList<>())
                .build();

        maestros.addAll(Arrays.asList(maestro1, maestro2));
    }
}
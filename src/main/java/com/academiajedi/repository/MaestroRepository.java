package com.academiajedi.repository;

import com.academiajedi.entity.Maestro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MaestroRepository {

    private final List<Maestro> maestros = new ArrayList<>();

    public List<Maestro> findAll() {
        return maestros;
    }

    public Optional<Maestro> findById(Long id) {
        return maestros.stream().filter(m -> m.getId().equals(id)).findFirst();
    }

    public void save(Maestro maestro) {
        maestros.add(maestro);
    }

    public void delete(Maestro maestro) {
        maestros.remove(maestro);
    }
}
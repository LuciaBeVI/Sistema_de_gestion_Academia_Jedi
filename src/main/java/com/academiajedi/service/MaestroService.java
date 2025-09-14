package com.academiajedi.service;

import com.academiajedi.dto.MaestroDTO;
import com.academiajedi.entity.Maestro;
import com.academiajedi.entity.Padawan;
import com.academiajedi.exception.NotFoundException;
import com.academiajedi.factory.MaestroFactory;
import com.academiajedi.repository.MaestroRepository;
import com.academiajedi.repository.PadawanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaestroService {

    private final MaestroRepository maestroRepo;
    private final PadawanRepository padawanRepo;

    public MaestroService(MaestroRepository maestroRepo, PadawanRepository padawanRepo) {
        this.maestroRepo = maestroRepo;
        this.padawanRepo = padawanRepo;
    }

    public List<Maestro> getAllMaestros() {
        return maestroRepo.findAll();
    }

    public Maestro getMaestroById(Long id) {
        return maestroRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Maestro no encontrado con ID: " + id));
    }

    public Maestro createMaestro(MaestroDTO dto) {
        long newId = maestroRepo.findAll().stream()
                .mapToLong(m -> m.getId() == null ? 0L : m.getId())
                .max()
                .orElse(0L) + 1L;

        Maestro m = MaestroFactory.createMaestro(newId, dto.getNombre(), dto.getEspecialidad());
        maestroRepo.save(m);
        return m;
    }

    public Maestro updateMaestro(Long id, MaestroDTO dto) {
        Maestro existing = getMaestroById(id);
        existing.setNombre(dto.getNombre());
        existing.setEspecialidad(dto.getEspecialidad());
        return existing;
    }

    public Maestro patchMaestro(Long id, String campo, String valor) {
        Maestro m = getMaestroById(id);
        switch (campo.toLowerCase()) {
            case "nombre":
                m.setNombre(valor);
                break;
            case "especialidad":
                m.setEspecialidad(valor);
                break;
            default:
                throw new IllegalArgumentException("Campo inválido: " + campo);
        }
        return m;
    }

    public void deleteMaestro(Long id) {
        Maestro m = getMaestroById(id);
        maestroRepo.delete(m);
    }

    public Maestro asignarPadawan(Long maestroId, Long padawanId) {
        Maestro m = getMaestroById(maestroId);
        Padawan p = padawanRepo.findById(padawanId)
                .orElseThrow(() -> new NotFoundException("Padawan no encontrado con ID: " + padawanId));
        if (m.getPadawans().stream().noneMatch(existing -> existing.getId().equals(p.getId()))) {
            m.getPadawans().add(p);
        }
        return m;
    }

    public List<Padawan> getPadawansByMaestro(Long maestroId) {
        Maestro m = getMaestroById(maestroId);
        return m.getPadawans();
    }

    public List<Maestro> getMaestrosByEspecialidad(String especialidad) {
        return maestroRepo.findAll().stream()
                .filter(m -> m.getEspecialidad() != null && m.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    public void addInitialData(List<Maestro> iniciales) {
        iniciales.forEach(maestroRepo::save);
    }
}
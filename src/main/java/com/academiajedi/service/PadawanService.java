package com.academiajedi.service;

import com.academiajedi.dto.PadawanDTO;
import com.academiajedi.entity.Padawan;
import com.academiajedi.exception.NotFoundException;
import com.academiajedi.factory.PadawanFactory;
import com.academiajedi.repository.PadawanRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PadawanService {

    private final PadawanRepository padawanRepo;

    public PadawanService(PadawanRepository padawanRepo) {
        this.padawanRepo = padawanRepo;
    }

    public List<Padawan> getAllPadawans() {
        return padawanRepo.findAll();
    }

    public Padawan getPadawanById(Long id) {
        return padawanRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Padawan no encontrado con ID: " + id));
    }

    public Padawan createPadawan(PadawanDTO dto) {
        long newId = padawanRepo.findAll().stream()
                .mapToLong(p -> p.getId() == null ? 0L : p.getId())
                .max()
                .orElse(0L) + 1L;

        Padawan p = PadawanFactory.createPadawan(newId, dto.getNombre(), dto.getRango(), dto.getEdad());
        padawanRepo.save(p); // <- no devuelve nada
        return p;
    }

    public Padawan updatePadawan(Long id, PadawanDTO dto) {
        Padawan existing = getPadawanById(id);
        existing.setNombre(dto.getNombre());
        existing.setRango(dto.getRango());
        existing.setEdad(dto.getEdad());
        return existing;
    }

    public Padawan patchPadawan(Long id, String campo, Object valor) {
        Padawan p = getPadawanById(id);
        switch (campo.toLowerCase()) {
            case "nombre":
                p.setNombre((String) valor);
                break;
            case "rango":
                p.setRango((String) valor);
                break;
            case "edad":
                if (!(valor instanceof Integer)) throw new IllegalArgumentException("Edad debe ser número");
                p.setEdad((Integer) valor);
                break;
            default:
                throw new IllegalArgumentException("Campo inválido: " + campo);
        }
        return p;
    }

    public void deletePadawan(Long id) {
        Padawan p = getPadawanById(id);
        padawanRepo.delete(p);
    }

    public List<Padawan> getPadawansByRango(String rango) {
        return padawanRepo.findAll().stream()
                .filter(p -> p.getRango() != null && p.getRango().equalsIgnoreCase(rango))
                .collect(Collectors.toList());
    }

    public void addInitialData(List<Padawan> iniciales) {
        iniciales.forEach(padawanRepo::save);
    }
}
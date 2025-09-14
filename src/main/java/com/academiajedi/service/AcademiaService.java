package com.academiajedi.service;

import com.academiajedi.repository.MaestroRepository;
import com.academiajedi.repository.PadawanRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AcademiaService {

    private final MaestroRepository maestroRepo;
    private final PadawanRepository padawanRepo;

    public AcademiaService(MaestroRepository maestroRepo, PadawanRepository padawanRepo) {
        this.maestroRepo = maestroRepo;
        this.padawanRepo = padawanRepo;
    }

    public Map<String, Object> getResumen() {
        int totalMaestros = maestroRepo.findAll().size();
        int totalPadawans = padawanRepo.findAll().size();

        double promedioPadawans = totalMaestros == 0 ? 0 :
                maestroRepo.findAll().stream()
                        .mapToInt(m -> m.getPadawans().size())
                        .average()
                        .orElse(0);

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalMaestros", totalMaestros);
        resumen.put("totalPadawans", totalPadawans);
        resumen.put("promedioPadawansPorMaestro", promedioPadawans);

        return resumen;
    }
}

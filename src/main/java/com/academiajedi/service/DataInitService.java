package com.academiajedi.service;

import com.academiajedi.factory.PadawanFactory;
import com.academiajedi.factory.MaestroFactory;
import com.academiajedi.repository.PadawanRepository;
import com.academiajedi.repository.MaestroRepository;
import com.academiajedi.entity.Padawan;
import com.academiajedi.entity.Maestro;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class DataInitService {

    private final PadawanRepository padawanRepo;
    private final MaestroRepository maestroRepo;

    public DataInitService(PadawanRepository padawanRepo, MaestroRepository maestroRepo) {
        this.padawanRepo = padawanRepo;
        this.maestroRepo = maestroRepo;
    }

    @PostConstruct
    public void init() {

        Padawan p1 = PadawanFactory.createPadawan(1L, "Luke Skywalker", "Novato", 19);
        Padawan p2 = PadawanFactory.createPadawan(2L, "Rey", "Aprendiz", 21);
        Padawan p3 = PadawanFactory.createPadawan(3L, "Ahsoka Tano", "Avanzado", 22);

        padawanRepo.save(p1);
        padawanRepo.save(p2);
        padawanRepo.save(p3);

        Maestro m1 = MaestroFactory.createMaestro(1L, "Yoda", "Fuerza");
        Maestro m2 = MaestroFactory.createMaestro(2L, "Obi-Wan Kenobi", "Sable de luz");

        m1.getPadawans().add(p1);
        m2.getPadawans().add(p2);
        m2.getPadawans().add(p3);

        maestroRepo.save(m1);
        maestroRepo.save(m2);
    }
}


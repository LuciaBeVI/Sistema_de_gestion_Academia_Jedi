package com.academiajedi.repository;

import com.academiajedi.entity.Padawan;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public class PadawanRepository {

    private final List<Padawan> padawans = new ArrayList<>();

    public List<Padawan> findAll() {
        return padawans;
    }

    public Optional<Padawan> findById(Long id) {
        return padawans.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void save(Padawan padawan) {
        padawans.add(padawan);
    }

    public void delete(Padawan padawan) {
        padawans.remove(padawan);
    }
}
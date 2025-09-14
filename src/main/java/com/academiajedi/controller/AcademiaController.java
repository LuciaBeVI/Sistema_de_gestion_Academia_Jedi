package com.academiajedi.controller;

import com.academiajedi.service.MaestroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/academia")
public class AcademiaController {

    private final MaestroService maestroService;

    public AcademiaController(MaestroService maestroService) {
        this.maestroService = maestroService;
    }

    @Operation(summary = "Obtiene un resumen de la academia")
    @GetMapping("/resumen")
    public ResponseEntity<Map<String, Object>> getResumen() {
        List<?> maestros = maestroService.getAllMaestros();
        int totalMaestros = maestros.size();
        int totalPadawans = maestroService.getAllMaestros().stream()
                .flatMap(m -> m.getPadawans().stream())
                .toList()
                .size();

        double promedio = totalMaestros == 0 ? 0.0 : (double) totalPadawans / totalMaestros;

        Map<String, Object> resumen = Map.of(
                "totalMaestros", totalMaestros,
                "totalPadawans", totalPadawans,
                "promedioPadawansPorMaestro", promedio
        );

        return ResponseEntity.ok(resumen);
    }
}
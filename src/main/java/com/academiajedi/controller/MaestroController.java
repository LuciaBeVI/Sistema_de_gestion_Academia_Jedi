package com.academiajedi.controller;

import com.academiajedi.dto.MaestroDTO;
import com.academiajedi.entity.Maestro;
import com.academiajedi.entity.Padawan;
import com.academiajedi.service.MaestroService;
import com.academiajedi.service.PadawanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maestros")
public class MaestroController {

    private final MaestroService service;
    private final PadawanService padawanService;

    public MaestroController(MaestroService service, PadawanService padawanService) {
        this.service = service;
        this.padawanService = padawanService;
    }

    @Operation(summary = "Obtiene todos los maestros")
    @GetMapping
    public ResponseEntity<List<Maestro>> getAllMaestros() {
        return ResponseEntity.ok(service.getAllMaestros());
    }

    @Operation(summary = "Filtra maestros por especialidad")
    @GetMapping("/filtro")
    public ResponseEntity<List<Maestro>> filterMaestros(@RequestParam String especialidad) {
        return ResponseEntity.ok(service.getMaestrosByEspecialidad(especialidad));
    }

    @Operation(summary = "Obtiene un maestro por ID")
    @GetMapping("/{id}")
    public ResponseEntity<Maestro> getMaestroById(@PathVariable Long id) {
        Maestro m = service.getMaestroById(id);
        return ResponseEntity.ok(m);
    }

    @Operation(summary = "Crea un nuevo maestro")
    @PostMapping
    public ResponseEntity<Maestro> createMaestro(@Valid @RequestBody MaestroDTO dto) {
        Maestro creado = service.createMaestro(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Reemplaza un maestro existente")
    @PutMapping("/{id}")
    public ResponseEntity<Maestro> updateMaestro(@PathVariable Long id, @Valid @RequestBody MaestroDTO dto) {
        Maestro actualizado = service.updateMaestro(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Actualiza un campo de un maestro")
    @PatchMapping("/{id}")
    public ResponseEntity<Maestro> patchMaestro(@PathVariable Long id, @RequestParam String campo, @RequestParam String valor) {
        Maestro result = service.patchMaestro(id, campo, valor);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un maestro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaestro(@PathVariable Long id) {
        service.deleteMaestro(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Asigna un padawan a un maestro")
    @ApiResponse(responseCode = "201", description = "Padawan asignado")
    @ApiResponse(responseCode = "404", description = "Maestro o Padawan no encontrado")
    @PostMapping("/{id}/padawans/{padawanId}")
    public ResponseEntity<Maestro> asignarPadawan(@PathVariable Long id, @PathVariable Long padawanId) {
        Maestro m = service.asignarPadawan(id, padawanId);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @Operation(summary = "Obtiene los padawans de un maestro")
    @GetMapping("/{id}/padawans")
    public ResponseEntity<List<Padawan>> getPadawansByMaestro(@PathVariable Long id) {
        List<Padawan> lista = service.getPadawansByMaestro(id);
        return ResponseEntity.ok(lista);
    }
}
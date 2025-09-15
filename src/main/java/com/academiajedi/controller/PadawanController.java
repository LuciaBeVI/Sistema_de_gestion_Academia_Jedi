package com.academiajedi.controller;

import com.academiajedi.dto.PadawanDTO;
import com.academiajedi.entity.Padawan;
import com.academiajedi.service.PadawanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/padawans")
public class PadawanController {

    private final PadawanService service;

    public PadawanController(PadawanService service) {
        this.service = service;
    }

    @Operation(summary = "Obtiene todos los padawans")
    @GetMapping
    public ResponseEntity<List<Padawan>> getAllPadawans() {
        return ResponseEntity.ok(service.getAllPadawans());
    }

    @Operation(summary = "Obtiene un padawan por ID")
    @ApiResponse(responseCode = "200", description = "Padawan encontrado")
    @ApiResponse(responseCode = "404", description = "Padawan no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<Padawan> getPadawanById(@PathVariable Long id) {
        Padawan p = service.getPadawanById(id);
        return ResponseEntity.ok(p);
    }

    @Operation(summary = "Filtra padawans por rango")
    @GetMapping("/filtro")
    public ResponseEntity<List<Padawan>> filterPadawans(@RequestParam String rango) {
        return ResponseEntity.ok(service.getPadawansByRango(rango));
    }

    @Operation(summary = "Crea un nuevo padawan")
    @ApiResponse(responseCode = "201", description = "Padawan creado")
    @ApiResponse(responseCode = "400", description = "Datos inválidos")
    @PostMapping
    public ResponseEntity<Padawan> createPadawan(
            @Valid @org.springframework.web.bind.annotation.RequestBody PadawanDTO dto) {

        Padawan creado = service.createPadawan(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Reemplaza un padawan existente")
    @ApiResponse(responseCode = "200", description = "Padawan actualizado")
    @ApiResponse(responseCode = "404", description = "Padawan no encontrado")
    @PutMapping("/{id}")
    public ResponseEntity<Padawan> updatePadawan(
            @PathVariable Long id,
            @Valid @org.springframework.web.bind.annotation.RequestBody PadawanDTO dto) {

        Padawan actualizado = service.updatePadawan(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Actualiza un campo de un padawan")
    @ApiResponse(responseCode = "200", description = "Campo actualizado")
    @ApiResponse(responseCode = "400", description = "Valor inválido")
    @PatchMapping("/{id}")
    public ResponseEntity<Padawan> patchPadawan(
            @PathVariable Long id,
            @RequestParam String campo,
            @RequestParam String valor) {

        Object v = campo.equalsIgnoreCase("edad") ? Integer.parseInt(valor) : valor;
        Padawan result = service.patchPadawan(id, campo, v);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Elimina un padawan")
    @ApiResponse(responseCode = "204", description = "Padawan eliminado")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePadawan(@PathVariable Long id) {
        service.deletePadawan(id);
        return ResponseEntity.noContent().build();
    }
}
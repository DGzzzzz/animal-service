package com.dg.animal_service.controllers;

import com.dg.animal_service.entidades.Animal;
import com.dg.animal_service.projections.RecebedorQtd;
import com.dg.animal_service.repositories.AnimalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RestController
@RequestMapping("/animais")
public class AnimalController {

    private AnimalRepository repository;

    public AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    private List<Animal> findAll() {
        return repository.findAll();
    }

    @PostMapping
    private Animal create(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @GetMapping("/not-adopted")
    private List<Animal> findNotAdopted() {
        return repository.findByAdopter();
    }

    @GetMapping("/resgates")
    private ResponseEntity<?> qtdAnimalPorRecebedor(
            @RequestParam LocalDate dataInicio,
            @RequestParam LocalDate dataFim
    ) {

        if (Period.between(dataInicio, dataFim).getYears() >= 1) {
            return ResponseEntity.badRequest().body("O intervalo máximo entre as datas é de um ano");
        }

        List<RecebedorQtd> resultado = repository.qtdAnimalPorRecebedor(dataInicio, dataFim);
        return ResponseEntity.ok(resultado);
    }
}

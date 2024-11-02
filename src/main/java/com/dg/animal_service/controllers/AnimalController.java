package com.dg.animal_service.controllers;

import com.dg.animal_service.entidades.Animal;
import com.dg.animal_service.projections.RecebedorQtd;
import com.dg.animal_service.repositories.AnimalRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping("/recebedor")
    private List<RecebedorQtd> qtdAnimalPorRecebedor() {
        LocalDate dataFim = LocalDate.now();
        LocalDate dataInicio = dataFim.minusYears(1);
        return repository.qtdAnimalPorRecebedor(dataInicio, dataFim);
    }
}

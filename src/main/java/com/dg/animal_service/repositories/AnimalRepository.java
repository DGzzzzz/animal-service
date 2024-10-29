package com.dg.animal_service.repositories;

import com.dg.animal_service.entidades.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("SELECT a FROM Animal a WHERE a.dataAdocao is NULL ORDER BY a.dataEntrada")
    List<Animal> findByAdopter();
}

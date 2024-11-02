package com.dg.animal_service.repositories;

import com.dg.animal_service.entidades.Animal;
import com.dg.animal_service.projections.RecebedorQtd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {
    @Query("SELECT a FROM Animal a WHERE a.dataAdocao is NULL ORDER BY a.dataEntrada")
    List<Animal> findByAdopter();

    @Query("SELECT a.nomeRecebedor AS nomeRecebedor, count(a) as qtd FROM Animal a WHERE a.dataEntrada BETWEEN :dataInicio AND :dataFim group by a.nomeRecebedor")
    List<RecebedorQtd> qtdAnimalPorRecebedor(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
            );
}

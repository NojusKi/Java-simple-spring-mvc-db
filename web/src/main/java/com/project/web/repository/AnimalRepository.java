package com.project.web.repository;

import com.project.web.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Optional<Animal> findByName(String name);

    @Query("SELECT a FROM Animal a WHERE a.name LIKE CONCAT('%', :query, '%')")
    List<Animal> searchAnimalsByName(String query);
}
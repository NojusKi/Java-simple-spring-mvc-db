package com.project.web.service;

import com.project.web.dto.Animaldto;
import com.project.web.models.Animal;

import java.util.List;

public interface AnimalService {
    List<Animaldto> findAllAnimals();

    Animal saveAnimal(Animaldto animaldto);

    Animaldto findAnimalById(long animalId);

    void updateAnimal(Animaldto animal);

    void delete(Long animalId);

    List<Animaldto> searchAnimalsByName(String query);
}

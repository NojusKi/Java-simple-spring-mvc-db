package com.project.web.service.impl;

import com.project.web.dto.Animaldto;
import com.project.web.models.Animal;
import com.project.web.repository.AnimalRepository;
import com.project.web.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    private AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public List<Animaldto> findAllAnimals() {
        List<Animal> animals = animalRepository.findAll();
        return animals.stream().map(animal -> mapToAnimaldto(animal)).collect(Collectors.toList());
    }

    @Override
    public Animal saveAnimal(Animaldto animaldto) {
        Animal animal = mapToAnimal(animaldto);
        return animalRepository.save(animal);
    }

    @Override
    public Animaldto findAnimalById(long animalId) {
        Animal animal = animalRepository.findById(animalId).get();
        return mapToAnimaldto(animal);
    }

    @Override
    public void updateAnimal(Animaldto animaldto) {
        Animal animal = mapToAnimal(animaldto);
        animalRepository.save(animal);
    }

    @Override
    public void delete(Long animalId) {
        animalRepository.deleteById(animalId);
    }

    @Override
    public List<Animaldto> searchAnimalsByName(String query) {
        List<Animal> animals = animalRepository.searchAnimalsByName(query);
        return animals.stream().map(animal -> mapToAnimaldto(animal)).collect(Collectors.toList());

    }

    private Animal mapToAnimal(Animaldto animal) {
        Animal animaldto = Animal.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .species(animal.getSpecies())
                .gender(animal.getGender())
                .build();
        return animaldto;
    }

    private Animaldto mapToAnimaldto(Animal animal) {
        Animaldto animaldto = Animaldto.builder()
                .id(animal.getId())
                .name(animal.getName())
                .age(animal.getAge())
                .species(animal.getSpecies())
                .gender(animal.getGender())
                .build();
        return animaldto;
    }
}


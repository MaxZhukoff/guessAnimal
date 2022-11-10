package com.guessanimal.dao;

import com.guessanimal.entity.Animal;

public class AnimalRepository extends RepositoryBase<Integer, Animal> {

    public AnimalRepository() {
        super(Animal.class);
    }
}

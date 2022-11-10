package com.guessanimal.service;

import com.guessanimal.dao.AnimalNodeRepository;
import com.guessanimal.dao.AnimalRepository;
import com.guessanimal.entity.Animal;
import com.guessanimal.entity.AnimalNode;

public class AnimalTreeService {

    private final AnimalRepository animalRepository = new AnimalRepository();
    private final AnimalNodeRepository animalNodeRepository = new AnimalNodeRepository();
    private AnimalNode rootNode;
    private AnimalNode lastNode;

    public AnimalTreeService() {
        AnimalNode node = animalNodeRepository.getRootNode().orElse(null);
        if (node == null) {
            rootNode = new AnimalNode();
            rootNode.setQuestion("живет на суше");

            AnimalNode rightNode = new AnimalNode();
            Animal cat = new Animal();
            cat.setName("Кот");
            rightNode.setValue(animalRepository.save(cat));
            animalNodeRepository.save(rightNode);
            rootNode.setRightChild(rightNode);

            AnimalNode leftNode = new AnimalNode();
            Animal whale = new Animal();
            whale.setName("Кит");
            leftNode.setValue(whale);
            animalNodeRepository.save(leftNode);
            rootNode.setLeftChild(leftNode);

            lastNode = rootNode;
            animalNodeRepository.save(rootNode);
        } else {
            rootNode = node;
            lastNode = rootNode;
        }
    }

    public void refreshTree() {
        lastNode = rootNode;
    }

    public AnimalNode getNextAnimalNode(boolean answer) {
        AnimalNode nextNode;
        AnimalNode childNode;
        if (answer) {
            childNode = lastNode.getRightChild();
        } else {
            childNode = lastNode.getLeftChild();
        }
        if (childNode != null) {
            lastNode = childNode;
            nextNode = childNode;
        } else {
            throw new RuntimeException();
        }
        return nextNode;
    }

        public void insertAnimalNode (String animalName, String question) {
            if (animalName.equals("") || question.equals("")) {
                throw new IllegalArgumentException();
            }
            AnimalNode leftNode = new AnimalNode();
            leftNode.setValue(lastNode.getValue());
            animalNodeRepository.save(leftNode);

            Animal animal = new Animal();
            animal.setName(animalName);

            AnimalNode rightNode = new AnimalNode();
            animalRepository.save(animal);
            rightNode.setValue(animal);
            animalNodeRepository.save(rightNode);

            lastNode.setValue(null);
            lastNode.setQuestion(question);
            lastNode.setRightChild(rightNode);
            lastNode.setLeftChild(leftNode);
            animalNodeRepository.update(lastNode);
        }

    public AnimalNode getLastNode() {
        return lastNode;
    }
}

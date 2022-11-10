package com.guessanimal;

import com.guessanimal.entity.Animal;
import com.guessanimal.entity.AnimalNode;
import com.guessanimal.service.AnimalTreeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GuessAnimal {
    private AnimalTreeService tree;

    public void startGame() {
        tree = new AnimalTreeService();

        try (BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in))) {
            String answer;
            AnimalNode lastNode;
            while (true) {
                System.out.println("Загадай животное, а я попробую угадать...");
                lastNode = askAnswer(consoleReader);

                Animal animal = lastNode.getValue();
                System.out.printf("Ты загадал %s? (Да/Нет): ", animal);
                answer = consoleReader.readLine();
                if (answer.equalsIgnoreCase("нет")) {
                    askNewAnimal(consoleReader, animal);
                } else {
                    System.out.printf("Замечательно, это %s!", animal);
                }

                System.out.println("Вы желаете продолжить игру? (Да/Нет): ");
                answer = consoleReader.readLine();
                if (!answer.equalsIgnoreCase("да")) {
                    break;
                }
                tree.refreshTree();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public AnimalNode askAnswer(BufferedReader consoleReader) throws IOException {
        System.out.printf("Это животное %s? (Да/Нет): ", tree.getLastNode().getQuestion());
        String answer = consoleReader.readLine();
        AnimalNode lastNode = tree.getNextAnimalNode(answer.equalsIgnoreCase("да"));
        if (lastNode.getQuestion() != null) {
            return askAnswer(consoleReader);
        } else {
            return lastNode;
        }
    }

    public void askNewAnimal(BufferedReader consoleReader, Animal animal) throws IOException {
        System.out.println("Я не смог отгадать животное :(");
        System.out.println("Какое животное вы загадали?");
        String animalName = consoleReader.readLine();
        System.out.printf("Чем %s отличается от %s?\n", animalName, animal);
        String answer = consoleReader.readLine();
        tree.insertAnimalNode(animalName, answer);
    }

    public static void main(String[] args) {
        GuessAnimal guessAnimal = new GuessAnimal();
        guessAnimal.startGame();
    }
}

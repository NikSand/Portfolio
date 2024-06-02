package com.example.task_4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Task4Application {

    public static void main(String[] args) {
        SpringApplication.run(Task4Application.class, args);

        String path = "D://test4.txt";
        List<Integer> parsenList = new ArrayList<>();
        List<Doska> mainList = new ArrayList<>();
        int minLength;
        int minWidth;
        boolean isPossible = true;

        try {
            List<String> strings = Files.readAllLines(Path.of(path));
            for (int i = 0; i < strings.size(); i++) {
                parsenList.add(Integer.parseInt(strings.get(i)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        mainList.add(new Doska((1), parsenList.get(0), parsenList.get(1)));
        mainList.add(new Doska((2), parsenList.get(2), parsenList.get(3)));
        mainList.add(new Doska((3), parsenList.get(4), parsenList.get(5)));
        mainList.add(new Doska((4), parsenList.get(6), parsenList.get(7)));
        mainList.add(new Doska((5), parsenList.get(8), parsenList.get(9)));
        mainList.add(new Doska((6), parsenList.get(10), parsenList.get(11)));

        System.out.println(" ");
        System.out.println("Доски для коробки: ");
        System.out.println(" ");

        for (Doska doska : mainList) {
            System.out.println("Доска: " + doska.id + " с длинной: " + doska.length +
                    " и шириной: " + doska.width);
        }

        minLength = mainList.get(0).getLength();
        minWidth = mainList.get(0).getWidth();

       for (int i = 0; i < mainList.size(); i++) {
           if (minLength > mainList.get(i).getLength()) {
               minLength = mainList.get(i).getLength();
           }
           if (minWidth > mainList.get(i).getWidth()) {
               minWidth = mainList.get(i).getWidth();
           }
       }

       System.out.println(" ");
       System.out.println("Минимальная длина доски: " + minLength + " ,максимальная ширина доски: " + minWidth);
       System.out.println(" ");

       for (int i = 0; i < mainList.size(); i++) {
           if (mainList.get(i).getLength() < minLength || mainList.get(i).getWidth() < minWidth) {
               isPossible = false;
           }
       }

       if (isPossible) {
           System.out.println("Возможно сделать коробку из досок!");
       } else {
           System.out.println("Невозможно сделать коробку из досок!");
       }


    }
}

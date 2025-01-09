package org.example;


import utils.ArrayUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Integer> numberList = new ArrayList<>();

        System.out.println("Введите расположение файла: ");

        Scanner scanner = new Scanner(System.in);


        String filePath = scanner.nextLine();

        scanner.close();

        try {

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String numbersLine;

            while ((numbersLine = reader.readLine()) != null) {
                String[] numbers = numbersLine.split(" ");
                for (String number : numbers) {
                    numberList.add(Integer.parseInt(number));
                }
            }

            reader.close();

            int totalMoves = ArrayUtils.calculateMoves(numberList);

            System.out.println(totalMoves);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Файл не найден!");
        }
    }
}

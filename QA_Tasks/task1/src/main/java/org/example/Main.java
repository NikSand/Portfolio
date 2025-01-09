package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите размер массива: ");
        int n = scanner.nextInt();

        System.out.print("Введите длину интервала: ");
        int m = scanner.nextInt();

        List<Integer> path = getPath(n, m);

        System.out.println("Полученный путь: " + path);

        scanner.close();
    }

    /**
     * Находит путь интервала у кругового массива.
     * @param n Размер массива.
     * @param m Длина интервала.
     * @return Путь интервала.
     */
    public static List<Integer> getPath(int n, int m) {
        List<Integer> path = new ArrayList<>();

        int currentIndex = 0;

        for (int i = 0; i < n; i++) {
            path.add(currentIndex + 1);

            currentIndex = (currentIndex + m - 1) % n;
        }

        return path;
    }
}
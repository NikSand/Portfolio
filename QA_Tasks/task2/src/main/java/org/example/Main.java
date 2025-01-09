package org.example;

import circle.Circle;
import point.Point;
import utils.Utils;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String circleFile = "file1.txt";
        String pointsFile = "file2.txt";

        try {
            Circle circle = Utils.readCircle(circleFile);

            List<Point> pointsList = Utils.readPointsPosition(pointsFile);

            Utils.resultPosition(circle, pointsList);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}

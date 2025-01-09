package utils;

import circle.Circle;
import point.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
Считывание информации из файлов.
 */
public class Utils {
    /**
     * Считывает из файла параметры круга.
     * @param filePath Расположение файла.
     * @return Объект класса круг.
     * @throws IOException Файл с информацией о круге не найден.
     */
    public static Circle readCircle(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String[] circleData = reader.readLine().split(" ");

            int centerX = Integer.parseInt(circleData[0]);
            int centerY = Integer.parseInt(circleData[1]);
            int radius = Integer.parseInt(reader.readLine());

            return new Circle(centerX, centerY, radius);
        }
    }

    /**
     * Считывает из файла координаты точек.
     * @param filePath Расположение файла.
     * @return Список объектов класса Точка.
     * @throws IOException Файл с информацией о точках не найден.
     */
    public static List<Point> readPointsPosition(String filePath) throws IOException {
        List<Point> pointsList = new ArrayList<>();
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

           while ((line = reader.readLine()) != null) {
               String[] parts = line.split(" ");

               int x = Integer.parseInt(parts[0]);
               int y = Integer.parseInt(parts[1]);

               pointsList.add(new Point(x, y));
           }
        }
        return pointsList;
    }

    /**
     * Выводит ответ о расположении точки в радиусе круга.
     * @param circle Информация о круге.
     * @param pointsList Список точек.
     */
    public static void resultPosition(Circle circle, List<Point> pointsList) {

        for (Point point : pointsList) {
            double distanceSquared = Math.pow(point.getX()
                    - circle.getX(), 2) + Math.pow(point.getY() - circle.getY(), 2);
            double radiusSquared = Math.pow(circle.getRadius(), 2);

            if (distanceSquared == radiusSquared) {
                System.out.println("0");
            } else if (distanceSquared < radiusSquared) {
                System.out.println("1");
            } else {
                System.out.println("2");
            }
        }
    }
}

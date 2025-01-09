package utils;

import java.util.Collections;
import java.util.List;

/**
 * Расчет числа шагов, необходимых для преобразований цифр в массиве.
 */
public class ArrayUtils {
    /**
     * Сортирует список и находит медианное значение.
     * @param numbersList Список цифр.
     * @return Медианное значение.
     */
    private static int findMedian(List<Integer> numbersList) {
        Collections.sort(numbersList);

        int n = numbersList.size();

        int median;

        if (n % 2 == 0) {
            median = (numbersList.get(n / 2 - 1) + numbersList.get(n / 2)) / 2;
        } else {
            median = numbersList.get(n / 2);
        }

        return median;
    }

    /**
     * Считает число шагов, необходимых для преобразований цифр в массиве.
     * @param numberList Отсортированный список цифр.
     * @return Число шагов, необходимых для преобразований цифр в массиве.
     */
    public static int calculateMoves(List<Integer> numberList) {
        int median = findMedian(numberList);

        int totalMoves = 0;

        for (int num : numberList) {
            totalMoves += Math.abs(num - median);
        }

        return totalMoves;
    }
}

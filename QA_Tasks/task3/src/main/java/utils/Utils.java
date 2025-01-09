package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Обработка JSON-файлов.
 */
public class Utils {
    /**
     * Поиск значения по id теста.
     * @param valuesNode Значение из файла values
     * @param id Номер теста.
     * @return Возвращает null, если значение не найдено. Или текущее значение узла.
     */
    private static String findValueById(JsonNode valuesNode, int id) {
        for (JsonNode valueNode : valuesNode) {
            if (valueNode.get("id").asInt() == id) {
                return valueNode.get("value").asText();
            }
        }
        return null;
    }

    /**
     * Объединение значений из файлов values и tests.
     * @param testsNode Значение из файла tests.
     * @param valuesNode Значение из файла values.
     */
    public static void fillValues(JsonNode testsNode, JsonNode valuesNode) {
        for (JsonNode test : testsNode) {
            int testId = test.get("id").asInt();

            String value = findValueById(valuesNode, testId);

            if (value != null) {
                ((ObjectNode) test).put("value", value);
            }

            // Если есть вложенные значения, рекурсивно вызываем метод.
            if (test.has("values")) {
                fillValues(test.get("values"), valuesNode);
            }
        }
    }
}

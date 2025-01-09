package org.example;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import utils.Utils;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String testsFilePath = "tests-edf09d69ff.json";
        String valuesFilePath = "values-c44d5c50d1.json";
        String reportFilePath = "report.json";

        try {
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode testsNode = objectMapper.readTree(new File(testsFilePath));
            JsonNode valuesNode = objectMapper.readTree(new File(valuesFilePath));

            // Создание нового JSON-узла для отчета.
            ObjectNode reportNode = objectMapper.createObjectNode();
            reportNode.set("tests", testsNode.get("tests").deepCopy());

            Utils.fillValues(reportNode.get("tests"), valuesNode.get("values"));

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(reportFilePath), reportNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
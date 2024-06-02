package com.example.task_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@SpringBootApplication
public class Task1Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1Application.class, args);

        String path  = "D://test.txt";
        Solution mySolution = new Solution();

        try {
            List<String> strings = Files.readAllLines(Path.of(path));
            for (String string : strings) {
                mySolution.result(string);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @TempDir
    Path tempDir; //временная директория

    // Тест записи файлов
    @Test
    void testWriteToFile() throws Exception {

        String outputPath = tempDir.toString();
        String fileName = "test_file.txt";
        List<String> data = Arrays.asList("str1", "str2", "str3");

        Main.writeToFile(outputPath, fileName, data, false);

        File file = new File(outputPath + File.separator + fileName);
        assertTrue(file.exists(), "Файл не был создан");

        List<String> lines = Files.readAllLines(file.toPath());
        assertEquals(data, lines, "Содержимое файла не совпадает с ожидаемым");
    }

    //тест разбики данных по типам
    @Test
    void testProcessInputData() {
        List<String> inputLines = Arrays.asList("123", "45.67", "str1", "42", "3.14", "str2");

        List<Integer> expectedIntegers = Arrays.asList(123, 42);
        List<Double> expectedFloats = Arrays.asList(45.67, 3.14);
        List<String> expectedStrings = Arrays.asList("str1", "str2");

        List<Integer> integers = new ArrayList<>();
        List<Double> floats = new ArrayList<>();
        List<String> strings = new ArrayList<>();

        //перебераем строки и ищем соответвие типов
        for (String line : inputLines) {
            try {
                integers.add(Integer.parseInt(line));
            } catch (NumberFormatException e1) {
                try {
                    floats.add(Double.parseDouble(line));
                } catch (NumberFormatException e2) {
                    strings.add(line);
                }
            }
        }

        // сверка
        assertEquals(expectedIntegers, integers);
        assertEquals(expectedFloats, floats);
        assertEquals(expectedStrings, strings);
    }

    //тест статистики
    @Test
    void testStatisticsCalculation() {
        List<Integer> integers = Arrays.asList(10, 20, 30, 40, 50);

        //ожидаемые значения
        int expectedMax = 50;
        int expectedMin = 10;
        int expectedSum = 150;
        double expectedAvg = 30.0;

        //вычисление
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int sum = 0;

        for (int value : integers) {
            if (value > max) max = value;
            if (value < min) min = value;
            sum += value;
        }
        double avg = (double) sum / integers.size();

        //сверка
        assertEquals(expectedMax, max);
        assertEquals(expectedMin, min);
        assertEquals(expectedSum, sum);
        assertEquals(expectedAvg, avg);
    }
}
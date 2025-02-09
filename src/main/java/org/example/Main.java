package org.example;


import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String inputPath = "input.txt";

        String outputPath = ".";
        String prefix = "result_";
        boolean adding = false;
        boolean statistics = false;
        boolean fullStatistics = false;

        // Считываем аргументы командной строки
        for (int i = 0; i < args.length; i++) {
            if ("-o".equals(args[i]) && i + 1 < args.length) {
                outputPath = args[++i];
            } else if ("-p".equals(args[i]) && i + 1 < args.length) {
                prefix = args[++i];
            } else if ("-a".equals(args[i])) {
                adding = true;
            } else if ("-s".equals(args[i])){
                statistics = true;
            }else if ("-f".equals(args[i])){
                fullStatistics = true;
            }
        }

        List<Double> floatsList = new ArrayList<>();
        List<String> stringsList = new ArrayList<>();
        List<Integer> integersList = new ArrayList<>();

        int maxInt = Integer.MIN_VALUE;
        int minInt = Integer.MAX_VALUE;
        int sumInt = 0;

        int maxStr = Integer.MIN_VALUE;
        int minStr = Integer.MAX_VALUE;

        //Чтение данных из файла и запись их в списки
        try {
            Scanner scanner = new Scanner(new File(inputPath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    int value = Integer.parseInt(line);
                    integersList.add(value);
                    if (value > maxInt) {
                        maxInt = value;
                    }
                    if (value < minInt) {
                        minInt = value;
                    }
                    sumInt += value;
                }catch (Exception e){
                    try{
                        double value = Double.parseDouble(line);
                        floatsList.add(value);
                    }catch (Exception e1){
                        stringsList.add(line);
                        if (line.length() > maxStr){
                            maxStr = line.length();
                        }
                        if (line.length() < minStr){
                            minStr = line.length();
                        }
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // Создаем директорию, если она не существует
        try {
            Files.createDirectories(Paths.get(outputPath));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Запись данных по файлам
        writeToFile(outputPath, prefix + "integers.txt", integersList, adding);
        writeToFile(outputPath, prefix + "floats.txt", floatsList, adding);
        writeToFile(outputPath, prefix + "strings.txt", stringsList, adding);

        if(statistics || fullStatistics){
            System.out.println("Float: " + floatsList.size());
            System.out.println("String: " + stringsList.size());
            System.out.println("Integer: " + integersList.size());
            if (fullStatistics){
                if(!integersList.isEmpty()){
                    System.out.println("******INT********");
                    System.out.println("Max: " + maxInt);
                    System.out.println("Min: " + minInt);
                    System.out.println("Sum: " + sumInt);
                    double avg = (double) sumInt / integersList.size();
                    System.out.println("Avg: " + avg);
                }
                if(!stringsList.isEmpty()){
                    System.out.println("******STR********");
                    System.out.println("Max: " + maxStr);
                    System.out.println("Min: " + minStr);
                }
           }
        }

    }
    public static void writeToFile(String outputPath, String fileName, List<?> data, boolean add) {
        if (data.isEmpty()){
            return;
        }

        try {
            FileWriter writer = new FileWriter(outputPath + File.separator + fileName, add);
            for (Object datum : data) {
                writer.write(datum.toString() + "\n");
            }
            writer.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}

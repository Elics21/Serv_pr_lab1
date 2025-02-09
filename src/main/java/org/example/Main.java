package org.example;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String path = "input.txt";
        List<Double> floatsList = new ArrayList<>();
        List<String> stringsList = new ArrayList<>();
        List<Integer> integersList = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    int value = Integer.parseInt(line);
                    integersList.add(value);
                }catch (Exception e){
                    try{
                        double value = Double.parseDouble(line);
                        floatsList.add(value);
                    }catch (Exception e1){
                        stringsList.add(line);
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        //Запись данных по файлам
        if (integersList.size() > 0) {
            try {
                FileWriter writer = new FileWriter("integers.txt");
                for (int i=0; i<integersList.size(); i++) {
                    writer.write(integersList.get(i).toString() + "\n");
                }
                writer.close();

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        if (floatsList.size() > 0) {
            try {
                FileWriter writer = new FileWriter("floats.txt");
                for (int i=0; i<floatsList.size(); i++) {
                    writer.write(floatsList.get(i).toString() + "\n");
                }
                writer.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (stringsList.size() > 0) {
            try {
                FileWriter writer = new FileWriter("strings.txt");
                for (int i=0; i<stringsList.size(); i++) {
                    writer.write(stringsList.get(i) + "\n");
                }
                writer.close();
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        System.out.println("Целые числа: " + integersList);
        System.out.println("Вещественные числа: " + floatsList);
        System.out.println("Строки: " + stringsList);
    }
}

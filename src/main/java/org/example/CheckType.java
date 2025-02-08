package org.example;

import java.io.File;
import java.util.Scanner;

public class CheckType {
    public void checkType(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                try {
                    Integer.parseInt(line);
                    System.out.println(line + " Целое Число");
                }catch (Exception e){
                    try{
                        Double.parseDouble(line);
                        System.out.println(line + " Вещественное Число");
                    }catch (Exception e1){
                        System.out.println(line + " Строка");
                    }
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

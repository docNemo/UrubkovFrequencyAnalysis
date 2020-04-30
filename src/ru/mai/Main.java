package ru.mai;

import java.io.*;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {
    private static Logger logger = Logger.getLogger("system");
    private static final byte ERROR_CODE = -1;

    public static void main(String[] args) {
        try {
            FileHandler fh = new FileHandler("errors.txt");
            logger.addHandler(fh);

        } catch (SecurityException e) {
            System.out.println("ERROR!");
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за политики безопасности.", e);
            System.exit(ERROR_CODE);


        } catch (IOException e) {
            System.out.println("ERROR!");
            logger.log(Level.SEVERE, "Не удалось создать файл лога из-за ошибки ввода-вывода.", e);
            System.exit(ERROR_CODE);
        }

        try {
            Scanner in = new Scanner(new File("src\\ru\\mai\\input.txt"));
            TreeMap<String, Integer> foundWords = calculateWords(in);
            List<Map.Entry<String, Integer>> resultList = foundWords.entrySet().stream().sorted(Comparator.nullsFirst(new MapComparator())).collect(Collectors.toList());

            for (Map.Entry<String, Integer> elem : resultList) {
                System.out.println(elem.getKey());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!");
            logger.log(Level.SEVERE, "Не найден файл", e);
            System.exit(ERROR_CODE);
        } catch (Exception e) {
            System.out.println("ERROR!");
            logger.log(Level.SEVERE, "Непредусмотренная ошибка", e);
            System.exit(ERROR_CODE);
        }
    }

    private static TreeMap calculateWords(Scanner in) {
        String[] words;
        TreeMap<String, Integer> foundWords = new TreeMap<>();
        while (in.hasNextLine()) {
            String inputStr = in.nextLine();
            if (!inputStr.isEmpty()) {
                words = inputStr.split("\\s+");

                for (String word : words) {
                    if (!foundWords.containsKey(word)) {
                        foundWords.put(word, 1);
                    } else {
                        foundWords.put(word, foundWords.get(word) + 1);
                    }
                }
            }
        }
        return foundWords;
    }
}

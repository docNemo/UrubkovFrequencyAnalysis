package ru.mai;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Main {
    private static final byte ERROR_CODE = -1;
    private static final String PATH_FILE = "src\\ru\\mai\\input.txt";

    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(new File(PATH_FILE));
            TreeMap<String, Integer> foundWords = calculateWords(in);
            List<Map.Entry<String, Integer>> resultList = foundWords.entrySet()
                                                                    .stream()
                                                                    .sorted(Comparator.nullsFirst(new MapComparator()))
                                                                    .collect(Collectors.toList());

            for (Map.Entry<String, Integer> elem : resultList) {
                System.out.println(elem.getKey());
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR!");
            System.exit(ERROR_CODE);
        } catch (Exception e) {
            System.out.println("ERROR!");
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

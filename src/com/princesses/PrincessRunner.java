package com.princesses;

import com.princesses.model.Princess;
import java.util.List;
import java.util.Scanner;
import static com.princesses.Util.addPrincess;
import static com.princesses.Util.deletePrincess;
import static com.princesses.Util.get;
import static com.princesses.Util.printPrincessList;
import static com.princesses.Util.readFile;
import static com.princesses.Util.updatePrincess;
import static java.lang.Integer.parseInt;

public class PrincessRunner {

    public static void main(String[] args) {
        String operations = """
                Choose your command:
                1. list
                2. get
                3. add
                4. delete
                5. update
                6. exit""";
        List<Princess> princesses = readFile();
        System.out.println("System ready.");
        System.out.println(operations);
        Scanner scanner = new Scanner(System.in);
        String yourChoice;
        do {
            yourChoice = scanner.nextLine();
            String[] fields;
            fields = yourChoice.split(" ");
            switch (fields[0].toLowerCase()) {
                case "list" -> {
                    printPrincessList(princesses);
                    System.out.println(operations);
                }
                case "get" -> {
                    try {
                        System.out.println(get(princesses, parseInt(fields[1])));
                    } catch (Exception e) {
                        System.err.println("There is no such princess");
                    } finally {
                        System.out.println(operations);
                    }
                }
                case "add" -> {
                    addPrincess(princesses, fields);
                    System.out.println(operations);
                }
                case "delete" -> {
                    Princess princess = get(princesses, parseInt(fields[1]));
                    String name = princess.getName();
                    if (!deletePrincess(princesses, parseInt(fields[1]))) {
                        System.err.println("There is no such princess");
                    }
                    else System.out.printf("Princess %s has been removed \n", name);
                    System.out.println(operations);
                }
                case "update" -> {
                    if (princesses.stream().map(Princess::getId).anyMatch(id -> id == parseInt(fields[1]))) {
                        updatePrincess(princesses, parseInt(fields[1]), fields);
                    } else {
                        System.err.println("There is no such princess");
                    }
                    System.out.println(operations);
                }
                case "exit" -> System.exit(0);
            }
        } while (true);
    }
}

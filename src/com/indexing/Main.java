package com.indexing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void commands() {
        System.out.println("Commands:");
        System.out.println("quit: quits application");
        System.out.println("add: adds files or directories for indexing");
        System.out.println("remove: removes files or directories from indexing");
        System.out.println("list: displays the files and directories available in the index");
        System.out.println("search: searches for a word or phrase in the index");
        System.out.print("\n");
        System.out.println("NOTE: Commands are case sensitive.");
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultString = new StringBuilder();
        
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultString.append(line).append("\n");
            }
        }

        return resultString.toString();
    }

    public static void main(String[] args) {
        // Write a Java application that provides a service for indexing text files.
        // Console interface should allow for
        // a. specifying the indexed files and directories and
        // b. querying files containing a given word.
        // Library should be extensible by the tokenization algorithm (simple splitting by words/support lexers/etc.).
        // State persistence between running sessions is not needed.
        // Providing some tests and a program with usage examples is advised.

        // Setting up the input scanner
        Scanner sc = new Scanner(System.in);

        String HELP = "Use the 'help' command for the list of commands available.";
        String MISSING_ERR = "Use the 'list' command to check for files and directories in the index.";
        String SIZE_ERR = "Use the 'add' command to add files or directories.";

        String START_PROCESS = "Running...";

        // Introducing the user to the application
        System.out.println("Welcome to text-file-indexing! A service for indexing text files.");
        commands();
        System.out.println(HELP);
        System.out.println("Happy indexing!");

        // state of the application
        boolean running = true;

        // initialise new index
        List<String> index = new ArrayList<String>();

        // check if state is running
        while(running) {
            // command input
            System.out.print("\n>> ");
            String command = sc.nextLine();

            // command preprocessing
            command = command.strip();

            // 'quit' commands
            if (command.equals("quit")) {
                System.out.print("Ending application...");
                running = false;
            }

            // 'help' command
            else if (command.equals("help")) {
                commands();
            }

            // 'add' command
            else if (command.equals("add")) {
                System.out.print("Enter file name to add: ");
                String fileName = sc.nextLine();
                System.out.println(START_PROCESS);

                index.add(fileName);
                System.out.println("Successfully added " + fileName + ".");

            }

            // 'remove' command
            else if (command.equals("remove")) {
                System.out.print("Enter file name to remove: ");

                String fileName = sc.nextLine();

                // file name pre-processing
                fileName = fileName.strip();

                // start remove process
                System.out.println(START_PROCESS);

                // check if file exists in index
                if (index.contains(fileName)) {
                    index.remove(fileName);
                    System.out.println("Successfully removed " + fileName + ".");
                }

                // error handling
                else {
                    System.out.println(fileName + " is not in the index.");
                    System.out.println(MISSING_ERR);
                }

            }

            // 'list' command
            else if (command.equals("list")) {
                // check for empty index
                if (index.size() > 0) {
                    for (String indexItem: index) {
                        System.out.println(indexItem);
                    }
                }

                // error handling
                else {
                    System.out.println("There are no files or directories in the index.");
                    System.out.println(SIZE_ERR);

                }

            }

            // error handling
            else {
                System.out.print(command);
                System.out.println(" is invalid.");
                System.out.println(HELP);
            }
        }


    }
}

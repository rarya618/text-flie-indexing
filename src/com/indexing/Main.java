package com.indexing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

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
	// write your code here
        // Write a Java application that provides a service for indexing text files.
        // Console interface should allow for
        // a. specifying the indexed files and directories and
        // b. querying files containing a given word.
        // Library should be extensible by the tokenization algorithm (simple splitting by words/support lexers/etc.).
        // State persistence between running sessions is not needed.
        // Providing some tests and a program with usage examples is advised.

        // Setting up the input scanner
        Scanner sc = new Scanner(System.in);

        // Introducing the user to the application
        System.out.println("Welcome to text-file-indexing!");
        System.out.println("Use 'help' for the list of commands available.");
        System.out.println("Happy indexing!");

        boolean running = true;

        while(running) {
            // command input
            System.out.print(">> ");
            String command = sc.nextLine();

            // command preprocessing
            command = command.strip();

            // 'quit' commands
            if (command.equals("quit")) {
                running = false;
            }

            // 'help' command
            else if (command.equals("help")) {
                System.out.println("Commands:");
                System.out.println("add: adds files for indexing");
                System.out.println("quit: quits application");
            }

            // 'add' command
            else if (command.equals("add")) {
                System.out.print("Enter file name: ");
                String fileName = sc.nextLine();
            }

            // error check
            else {
                System.out.println(command);
                System.out.println("Invalid command");
            }
        }


    }
}

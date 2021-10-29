package com.indexing;

import java.io.*;
import java.util.*;

public class Main {

    // create list of commands
    public static void commands() {
        System.out.println("Commands:");
        System.out.println("quit: quits application");
        System.out.println("add: adds files or directories for indexing");
        System.out.println("rm: removes files or directories from indexing");
        System.out.println("read: reads the specified file in the index");
        System.out.println("ls: displays the files and directories available in the index");
        System.out.println("search: searches for a word or phrase in the index");
        System.out.print("\n");
        System.out.println("NOTE: Commands are case sensitive.");
    }

    // print when a command is invalid
    public static void invalidCommand(String command) {
        System.out.println(command + " is invalid.");
    }

    // process given file
    public static File processFile(String filename) {
        File file = new File(filename);

        if (file.exists()) {
            return file;
        }

        else {
            return null;
        }
    }

    // reads given file
    public static void readFile(File file) throws FileNotFoundException {
        Scanner currentScanner = new Scanner(file);

        if (currentScanner.hasNext()) {
            System.out.println(currentScanner.nextLine());
        }
    }

    public static void processDirectory(File directory) throws FileNotFoundException {
        File[] fileList = directory.listFiles();

        for (File subFile: Objects.requireNonNull(fileList)) {
            // for files
            if (subFile.isFile()) {
                System.out.format("File: %s%n", subFile.getName());
                readFile(subFile);
            }

            // for sub-directories
            else if (subFile.isDirectory()) {
                processDirectory(subFile);
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // provides a service for indexing text files.
        // Console interface should allow for
        // a. specifying the indexed files and directories and
        // b. querying files containing a given word.
        // Library should be extensible by the tokenization algorithm (simple splitting by words/support lexers/etc.).
        // State persistence between running sessions is not needed.
        // Providing some tests and a program with usage examples is advised.

        // Setting up the input scanner
        Scanner sc = new Scanner(System.in);

        String HELP = "Use the 'help' command for the list of commands available.";
        String MISSING_ERR = "Use the 'ls' command to check for files and directories in the index.";
        String SIZE_ERR = "Use the 'add' command to add files or directories.";

        // Introducing the user to the application
        System.out.println("Welcome to text-file-indexing! A service for indexing text files.");

        // print list of commands
        commands();

        System.out.println(HELP);
        System.out.println("Happy indexing!");

        // state of the application
        boolean running = true;

        // initialise new index
        Map<String, File> index = new HashMap<>();

        // run if state is running
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
            else if (command.contains("add")) {
                String fileName;
                String[] splitCommand = command.split(" ");

                if (splitCommand[0].equals("add")) {
                    StringBuilder temp = new StringBuilder();

                    // command line argument processing
                    if (splitCommand.length > 1) {
                        for (int i = 1; i < splitCommand.length; i++) {
                            temp.append(splitCommand[i]).append(" ");
                        }

                        fileName = temp.toString().strip();
                    }

                    // if argument is missing
                    else {
                        System.out.print("Enter file name to add: ");
                        fileName = sc.nextLine();
                    }

                }

                // invalid command check
                else {
                    invalidCommand(command);
                    continue;
                }

                // check if file exists
                if (processFile(fileName) != null) {
                    index.put(fileName, processFile(fileName));
                    System.out.println("Successfully added " + fileName + ".");
                }

                // error handling
                else {
                    System.out.println(fileName + " does not exist.");
                }

            }

            // 'remove' command
            else if (command.contains("rm")) {
                String fileName;
                String[] splitCommand = command.split(" ");

                if (splitCommand[0].equals("rm")) {
                    StringBuilder temp = new StringBuilder();

                    // command line argument processing
                    if (splitCommand.length > 1) {
                        for (int i = 1; i < splitCommand.length; i++) {
                            temp.append(splitCommand[i]).append(" ");
                        }

                        fileName = temp.toString().strip();
                    }

                    // if argument is missing
                    else {
                        System.out.print("Enter file name to remove: ");
                        fileName = sc.nextLine();
                    }

                }

                // invalid command check
                else {
                    invalidCommand(command);
                    continue;
                }

                // check if file exists in index
                if (index.containsKey(fileName)) {
                    index.remove(fileName);
                    System.out.println("Successfully removed " + fileName + ".");
                }

                // error handling
                else {
                    System.out.println(fileName + " is not in the index.");
                    System.out.println(MISSING_ERR);
                }

            }

            // 'read' command
            else if (command.contains("read")) {
                String fileName;
                String[] splitCommand = command.split(" ");

                if (splitCommand[0].equals("read")) {
                    StringBuilder temp = new StringBuilder();

                    // command line argument processing
                    if (splitCommand.length > 1) {
                        for (int i = 1; i < splitCommand.length; i++) {
                            temp.append(splitCommand[i]).append(" ");
                        }

                        fileName = temp.toString().strip();
                    }

                    // if argument is missing
                    else {
                        System.out.print("Enter file name to read: ");
                        fileName = sc.nextLine();
                    }

                }

                // invalid command check
                else {
                    invalidCommand(command);
                    continue;
                }

                // check if file exists in index
                if (index.containsKey(fileName)) {
                    File current = index.get(fileName);

                    // checking for directory
                    if (current.isDirectory()) {
                        processDirectory(current);
                    }

                    // file reading
                    else {
                        readFile(current);
                    }

                }

                // error handling
                else {
                    System.out.println(fileName + " is not in the index.");
                    System.out.println(MISSING_ERR);
                }

            }

            // 'list' command
            else if (command.equals("ls")) {
                // check for empty index
                if (index.size() > 0) {
                    for (String item: index.keySet()) {
                        System.out.println(item);
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
                invalidCommand(command);
                System.out.println(HELP);
            }
        }

        // close scanner
        sc.close();
    }
}

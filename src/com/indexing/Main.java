package com.indexing;

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
        

    }
}

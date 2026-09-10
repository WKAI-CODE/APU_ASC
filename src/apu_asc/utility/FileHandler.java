package apu_asc.utility;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {

    // Read file
    // Read all data from text file
    // and return data as an ArrayList<String>
    public static ArrayList<String> readFile(String fileName) {

        // Create an empty ArrayList
        ArrayList<String> data = new ArrayList<>();

        try {

            // Create File object
            File file = new File(fileName);

            // Check whether file exists
            if (!file.exists()) {

                // Create a new empty file
                file.createNewFile();

                return data;
            }

            // Open file for reading
            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            // Read file line by line
            while ((line = br.readLine()) != null) {

                // Ignore empty lines
                if (!line.trim().isEmpty()) {

                    data.add(line);
                }
            }

            // Close file
            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error reading file: " + e.getMessage()
            );
        }

        // Return the ArrayList
        return data;
    }


    // Append file
    // Add new data to the end of the file
    public static void appendFile(String fileName, String data) {

        try {

            // true means append
            // It will NOT delete existing data
            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(fileName, true)
                    );

            // Write new data
            bw.write(data);

            // Move to next line
            bw.newLine();

            // Close file
            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error writing file: " + e.getMessage()
            );
        }
    }


    //WRITE FILE

    public static void writeFile(
            String fileName,
            ArrayList<String> data) {

        try {

            // Open file for writing
            BufferedWriter bw =
                    new BufferedWriter(
                            new FileWriter(fileName)
                    );

            // Write all data
            for (String line : data) {

                bw.write(line);

                // Move to next line
                bw.newLine();
            }

            // Close file
            bw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error writing file: " + e.getMessage()
            );
        }
    }
    
    // Check whether an ID already exists
    public static boolean recordExists(
            String fileName,
            String recordID) {

        ArrayList<String> data =
                readFile(fileName);

        for (int i = 0; i < data.size(); i++) {

            String line = data.get(i);

            if (line.startsWith(recordID + "|")) {
                return true;
            }
        }

        return false;
    }


    // Replace an existing record
    public static boolean updateRecord(
            String fileName,
            String recordID,
            String newLine) {

        ArrayList<String> data =
                readFile(fileName);

        for (int i = 0; i < data.size(); i++) {

            String line = data.get(i);

            if (line.startsWith(recordID + "|")) {

                data.set(i, newLine);
                writeFile(fileName, data);

                return true;
            }
        }

        return false;
    }


    // Delete an existing record
    public static boolean deleteRecord(
            String fileName,
            String recordID) {

        ArrayList<String> data =
                readFile(fileName);

        for (int i = 0; i < data.size(); i++) {

            String line = data.get(i);

            if (line.startsWith(recordID + "|")) {

                data.remove(i);
                writeFile(fileName, data);

                return true;
            }
        }

        return false;
    }
    
    public static String[] readFileToArray(String fileName) {
        
        ArrayList<String> data = readFile(fileName);
        
        String[] lines = new String[data.size()];
        
        for (int i=0; i < data.size(); i++) {
            lines[i] = data.get(i);
        }
        
        return lines;
    }
}

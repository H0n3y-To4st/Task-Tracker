package playground;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.util.Scanner;

import java.io.IOException;


public class FileTesting {

    public void tests() {
        readFile(fileCreation());
        fileDeletion(fileCreation());
    }

    public File fileCreation() {
        // This is only creating a reference to a file object
        File directory = new File("src/playground/files");
        File testFile = null;
        try {
            if (!directory.exists()) {
                directory.mkdir();
            }
            testFile = new File(directory, "test.txt");
            // Here we can create the file itself and check first if the same name exists
            if (testFile.createNewFile()) {
                System.out.println("File being created....");
            } else {
                System.out.println("File already exists...");
            }
            System.out.println("File created....Writing into file...");

            // As we are writing very little, makes no sense to use a bufferedWriter for performance yet
            FileWriter fw = new FileWriter(testFile);
            fw.write("Hello World");
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong when trying to write a file");
            e.printStackTrace();
        }
        return testFile;
    }

    public void readFile(File file) {
        try {
            System.out.println("Reading file...");
            // As there is little input, using this instead of FileReader
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong when reading file");
            e.printStackTrace();
        }
    }

    public void fileDeletion(File file) {
        try {
            System.out.println("Deleting file...");
            if (file.delete()) {
                System.out.println("File deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

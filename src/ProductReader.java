import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        Scanner inFile;
        String line;
        Path target = new File(System.getProperty("user.dir")).toPath();
        target = target.resolve("src");
        chooser.setCurrentDirectory(target.toFile());

        try  // Code that might trigger the exception goes here
        {
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                target = chooser.getSelectedFile().toPath();

                inFile = new Scanner(target.toFile());

                // Column headers
                System.out.printf("%-8s %-12s %-25s %-8s%n", "ID#", "Name", "Description", "Cost");
                System.out.println("==========================================================");

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length == 4) {
                        System.out.printf("%-8s %-12s %-25s %-8s%n",
                                parts[0], parts[1], parts[2], parts[3]);
                    }
                }

                inFile.close();
            } else {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
            e.printStackTrace();
        }
        catch (IOException e) // code to handle this exception
        {
            System.out.println("IOException Error");
            e.printStackTrace();
        }
    }
}

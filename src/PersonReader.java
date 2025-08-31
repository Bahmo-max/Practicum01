import javax.swing.*;
import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class PersonReader
{
    public static void main(String[] args)
    {
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

                System.out.printf("%-8s %-12s %-12s %-8s %-6s%n",
                        "ID#", "Firstname", "Lastname", "Title", "YOB");
                System.out.println("============================================================");

                while (inFile.hasNextLine()) {
                    line = inFile.nextLine();
                    String[] parts = line.split(",");

                    if (parts.length == 5) {
                        String formatted = String.format("%-8s %-12s %-12s %-8s %-6s",
                                parts[0], parts[1], parts[2], parts[3], parts[4]);
                        System.out.println(formatted);
                    }
                }

                inFile.close();
            } else {
                System.out.println("Sorry, you must select a file! Terminating!");
                System.exit(0);
            }
        }
        catch (FileNotFoundException e)
        {
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

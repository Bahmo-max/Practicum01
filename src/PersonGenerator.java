import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator
{
    public static void main(String[] args) {

        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\personData.txt");

        boolean done = false;
        String ID = "";
        String firstname = "";
        String lastname = "";
        String title = "";
        int YOB = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in,"Enter the ID {6 digits}");
            firstname = SafeInput.getNonZeroLenString(in,"Enter the first name: ");
            lastname = SafeInput.getNonZeroLenString(in,"Enter the last name: ");
            title = SafeInput.getNonZeroLenString(in,"Enter the title: ");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth", 1000, 9999);

            String personRec = ID + "," + firstname + "," + lastname + "," + title + "," + YOB;


            folks.add(personRec);

            done = SafeInput.getYNConfirm(in,"Are you done?");
        } while(!done);

        // Echo back to console
        for(String p : folks)
            System.out.println(p);

        try
        {
            // Typical java pattern of inherited classes
            // we wrap a BufferedWriter around a lower level BufferedOutputStream
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            // Finally can write the file LOL!
            for(String rec : folks)
            {
                writer.write(rec, 0, rec.length());  // write the full record
                writer.newLine();  // adds the new line
            }

            writer.close(); // must close the file
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}

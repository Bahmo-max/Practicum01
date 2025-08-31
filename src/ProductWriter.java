import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();

        // Pre-filled product records so it's easier than writing it each run which is kind of time waster
        products.add("000001,Pipeweed,Long Bottom Leaf,600.0");
        products.add("000002,Lembas,Elven Wayfare Bread,200.0");
        products.add("000003,Wine,Woodland Elf Wine,400.0");
        products.add("000004,Mushrooms,Farmer Took's Finest,125.0");
        products.add("000005,Mithril,Enchanted Dwarven Armor,3000.0");

        Path file = Paths.get("src\\ProductTestData.txt");

        try {
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : products) {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }

            writer.close();
            System.out.println("Product file written successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


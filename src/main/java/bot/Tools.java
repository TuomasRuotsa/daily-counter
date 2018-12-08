package bot;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Tools {

    public static String readEkaFile() {
        try {
            String contents = new String(Files.readAllBytes(Paths.get("test.json")));
            return contents;
        } catch (IOException ioe) {
            return "";
        }
    }

    public static String readStringFromURL(String requestURL)
    {
        try (Scanner scanner = new Scanner(new URL(requestURL).openStream(),
                StandardCharsets.UTF_8.toString()))
        {
            scanner.useDelimiter("\\A");
            String returnValue = scanner.hasNext() ? scanner.next() : "";
            scanner.close();
            return returnValue;
        } catch (IOException ioe) {
            return "Couldn't fetch data, nothing is up.";
        }
    }
}

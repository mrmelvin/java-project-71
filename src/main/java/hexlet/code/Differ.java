package hexlet.code;

import java.io.IOException;
import java.util.Map;

public class Differ {

    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object[]> preFormatted = Parser.parse(filePath1, filePath2);
        return Formatter.format(preFormatted, "stylish");
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object[]> preFormatted = Parser.parse(filePath1, filePath2);
        return Formatter.format(preFormatted, formatName);
    }
}

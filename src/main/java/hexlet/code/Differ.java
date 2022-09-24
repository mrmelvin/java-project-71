package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Differ {

    public static Map readingFile(String filePath) throws IOException {
        Map<String, Object> fileToMap = new HashMap<>();
        Path pathToBasicFile = Paths.get(filePath).isAbsolute() ? Paths.get(filePath)
                : Paths.get(filePath).toAbsolutePath();
        if (Files.exists(pathToBasicFile)) {
            if (pathToBasicFile.toString().endsWith("json")) {
                ObjectMapper objectMapper = new ObjectMapper();
                fileToMap = objectMapper.readValue(new File(pathToBasicFile.toString()), Map.class);
            } else if (pathToBasicFile.toString().endsWith("yml")) {
                ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
                fileToMap = objectMapper.readValue(new File(pathToBasicFile.toString()), Map.class);
            }
        } else {
            return null;
        }
        return fileToMap;
    }


    public static String generate(String filePath1, String filePath2) throws IOException {
        Map<String, Object[]> preFormatted = Parser.parse(readingFile(filePath1), readingFile(filePath2));
        return Formatter.format(preFormatted, "stylish");
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Map<String, Object[]> preFormatted = Parser.parse(readingFile(filePath1), readingFile(filePath2));
        return Formatter.format(preFormatted, formatName);
    }
}

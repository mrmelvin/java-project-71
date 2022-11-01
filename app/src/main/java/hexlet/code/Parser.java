package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {

    public static Map jsonReader(Path filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath.toString()), Map.class);
    }

    public static Map yamlReader(Path filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(filePath.toString()), Map.class);
    }
    public static Map parse(Path fullPath, String formatType) throws IOException {
        Map<String, Object> fileToMap = new HashMap<>();
        switch (formatType) {
            case "json":
                fileToMap = jsonReader(fullPath);
                break;
            case "yml":
                fileToMap = yamlReader(fullPath);
                break;
            default:
                System.out.println("Unsupported file format");
                break;
        }
        return fileToMap;
    }
}

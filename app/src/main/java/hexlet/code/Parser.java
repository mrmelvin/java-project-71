package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map parse(Path fullPath, String formatType) throws IOException {
        Map<String, Object> fileToMap = new HashMap<>();
        ObjectMapper objectMapper = null;
        switch (formatType) {
            case "json":
                objectMapper = new ObjectMapper();
                fileToMap = objectMapper.readValue(new File(fullPath.toString()), Map.class);
                break;
            case "yml":
                objectMapper = new ObjectMapper(new YAMLFactory());
                fileToMap = objectMapper.readValue(new File(fullPath.toString()), Map.class);
                break;
            default:
                System.out.println("Unsupported file format");
                break;
        }
        return fileToMap;
    }
}

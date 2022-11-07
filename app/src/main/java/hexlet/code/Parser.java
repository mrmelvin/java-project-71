package hexlet.code;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {


    public static Map jsonReader(String inputData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputData, Map.class);
    }

    public static Map yamlReader(String inputData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(inputData, Map.class);
    }
    public static Map parse(String content, String formatType) throws IOException {
        Map<String, Object> fileToMap = new HashMap<>();
        switch (formatType) {
            case "json":
                fileToMap = jsonReader(content);
                break;
            case "yml":
                fileToMap = yamlReader(content);
                break;
            default:
                System.out.println("Unsupported content type");
                break;
        }
        return fileToMap;
    }
}

package hexlet.code.formatstyle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class FormatterJSON {
    public static String getDataJson(Map<String, Map<String, Object[]>> inputData) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(inputData);
    }
}

package hexlet.code.formatstyle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Objects;

public class FormatterJSON {
    public static String getDataJson(Map<String, Object[]> inputData) throws IOException {

        Map<String, Map<String, Object>> output = new LinkedHashMap<>();
        Map<String, Object> addedMap = new TreeMap<>();
        Map<String, Object> deletedMap = new TreeMap<>();
        Map<String, Object> changedMap = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[SupportFormatter.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(0)) {
                addedMap.put(elem.getKey(), elem.getValue()[SupportFormatter.INDEX_SECOND_FILE_DATA].toString());
            } else if (elem.getValue()[SupportFormatter.INDEX_SECOND_FILE_AVAILABLE_KEY].equals(0)) {
                deletedMap.put(elem.getKey(), elem.getValue()[SupportFormatter.INDEX_FIRST_FILE_DATA].toString());
            } else if (!Objects.equals(elem.getValue()[SupportFormatter.INDEX_FIRST_FILE_DATA],
                    elem.getValue()[SupportFormatter.INDEX_SECOND_FILE_DATA])) {
                String[] changedValues = new String[]{
                        SupportFormatter.presentNullToString(elem.getValue()[SupportFormatter.INDEX_FIRST_FILE_DATA]),
                        SupportFormatter.presentNullToString(elem.getValue()[SupportFormatter.INDEX_SECOND_FILE_DATA])};
                changedMap.put(elem.getKey(), Arrays.toString(changedValues));
            }
        }
        output.put("added", addedMap);
        output.put("deleted", deletedMap);
        output.put("changed", changedMap);

        return objectMapper.writeValueAsString(output);
    }
}

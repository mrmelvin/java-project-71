package hexlet.code.formatstyle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Objects;

public class FormatterJSON {
    public static String getDataJson(Map<String, Object[]> inputData) {

        Map<String, Map<String, Object>> output = new LinkedHashMap<>();
        Map<String, Object> addedMap = new TreeMap<>();
        Map<String, Object> deletedMap = new TreeMap<>();
        Map<String, Object> changedMap = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(0)) {
                addedMap.put(elem.getKey(), elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA].toString());
            } else if (elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_AVAILABLE_KEY].equals(0)) {
                deletedMap.put(elem.getKey(), elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_DATA].toString());
            } else if (!Objects.equals(elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_DATA],
                    elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA])) {
                String[] changedValues = new String[]{
                        FormatterDefault.presentNullToString(elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_DATA]),
                        FormatterDefault.presentNullToString(elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA])};
                changedMap.put(elem.getKey(), Arrays.toString(changedValues));
            }
        }
        output.put("added", addedMap);
        output.put("deleted", deletedMap);
        output.put("changed", changedMap);

        try {
            return objectMapper.writeValueAsString(output);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

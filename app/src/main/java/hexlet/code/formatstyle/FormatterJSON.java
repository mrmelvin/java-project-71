package hexlet.code.formatstyle;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class FormatterJSON {
    public static String getDataJson(Map<String, Map<String, Object[]>> inputData) throws IOException {

        Map<String, Map<String, ?>> output = new LinkedHashMap<>();
        Map<String, Object> addedMap = new TreeMap<>();
        Map<String, Object> deletedMap = new TreeMap<>();
        Map<String, Map<String, String>> changedMap = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder str = new StringBuilder();
        String s = "";

        for (var firstKey: inputData.entrySet()) {
            for (var changingElement: firstKey.getValue().entrySet()) {
                switch (changingElement.getKey()) {
                    case SupportFormatter.ADDED:
                        addedMap.put(firstKey.getKey(), changingElement.getValue()[0].toString());
                        break;
                    case SupportFormatter.DELETED:
                        deletedMap.put(firstKey.getKey(), changingElement.getValue()[0].toString());
                        break;
                    case SupportFormatter.CHANGED:
                        Map<String, String> changingValuesMap = new LinkedHashMap<>();
                        changingValuesMap.put("past",
                                            SupportFormatter.presentNullToString(changingElement.getValue()[0]));
                        changingValuesMap.put("new",
                                            SupportFormatter.presentNullToString(changingElement.getValue()[1]));
                        changedMap.put(firstKey.getKey(), changingValuesMap);
                        break;
                    default:
                        continue;
                }
            }
        }
        output.put("added", addedMap);
        output.put("deleted", deletedMap);
        output.put("changed", changedMap);
        return objectMapper.writeValueAsString(output);
    }
}

package hexlet.code;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Formatter {

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static final int INDEX_SECOND_FILE_DATA = 3;

    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";

    public static final String JSON_FORMAT = "json";

    public static String deletingNullPointerExceptions(Object inpitData) {
        return inpitData == null ? "null" : inpitData.toString();
    }

    public static String quotesNearValue(Object inpitData) {
        if (inpitData instanceof ArrayList<?> | inpitData instanceof Map<?, ?> | inpitData instanceof Set<?>) {
            return "[complex value]";
        } else if (inpitData == null) {
            return "null";
        } else if (inpitData instanceof String) {
            return "'" + inpitData + "'";
        } else {
            return inpitData.toString();
        }
    }

    public static String getDataDefault(Map<String, Object[]> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[0].equals(1) & elem.getValue()[1].equals(1)) {
                if (Objects.equals(elem.getValue()[2], elem.getValue()[INDEX_SECOND_FILE_DATA])) {
                    output.append(PREFIX_EQUAL_DATA + elem.getKey());
                    output.append(": ");
                    output.append(deletingNullPointerExceptions(elem.getValue()[2]));
                    output.append("\n");
                } else {
                    output.append(PREFIX_DELETE_DATA + elem.getKey());
                    output.append(": ");
                    output.append(deletingNullPointerExceptions(elem.getValue()[2]));
                    output.append("\n");
                    output.append(PREFIX_ADD_DATA + elem.getKey());
                    output.append(": ");
                    output.append(deletingNullPointerExceptions(elem.getValue()[INDEX_SECOND_FILE_DATA]));
                    output.append("\n");
                }
            } else if (elem.getValue()[0].equals(0)) {
                output.append(PREFIX_ADD_DATA + elem.getKey());
                output.append(": ");
                output.append(deletingNullPointerExceptions(elem.getValue()[INDEX_SECOND_FILE_DATA]));
                output.append("\n");
            } else {
                output.append(PREFIX_DELETE_DATA + elem.getKey());
                output.append(": ");
                output.append(deletingNullPointerExceptions(elem.getValue()[2]));
                output.append("\n");
            }
        }
        output.append("}");
        return output.toString();
    }

    public static String getDataPlain(Map<String, Object[]> inputData) {
        StringBuilder output = new StringBuilder("");
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[0].equals(0)) {
                output.append("Property " + quotesNearValue(elem.getKey())
                        + " was added with value: "
                        + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            } else if (elem.getValue()[1].equals(0)) {
                output.append("Property " + quotesNearValue(elem.getKey()) + " was removed\n");
            } else if (!Objects.equals(elem.getValue()[2], elem.getValue()[INDEX_SECOND_FILE_DATA])) {
                output.append("Property " + quotesNearValue(elem.getKey()) + " was updated. From "
                            + quotesNearValue(elem.getValue()[2])
                            + " to " + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            }
        }
        return output.toString();
    }

    public static String getDataJson(Map<String, Object[]> inputData) {

        Map<String, Map<String, Object>> output = new LinkedHashMap<>();
        Map<String, Object> addedMap = new TreeMap<>();
        Map<String, Object> deletedMap = new TreeMap<>();
        Map<String, Object> changedMap = new TreeMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[0].equals(0)) {
                addedMap.put(elem.getKey(), elem.getValue()[INDEX_SECOND_FILE_DATA].toString());
            } else if (elem.getValue()[1].equals(0)) {
                deletedMap.put(elem.getKey(), elem.getValue()[2].toString());
            } else if (!Objects.equals(elem.getValue()[2], elem.getValue()[INDEX_SECOND_FILE_DATA])) {
                String[] changedValues = new String[]{deletingNullPointerExceptions(elem.getValue()[2]),
                        deletingNullPointerExceptions(elem.getValue()[INDEX_SECOND_FILE_DATA])};
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

    public static String format(Map<String, Object[]> inputData, String currentFormat) {
        String diffString = "";
        switch (currentFormat) {
            case "stylish":
                diffString = getDataDefault(inputData);
                break;
            case "plain":
                diffString = getDataPlain(inputData);
                break;
            case "json":
                diffString = getDataJson(inputData);
                break;
            default:
                diffString = "Incorrect format type";
                break;
        }
        return diffString;
    }
}

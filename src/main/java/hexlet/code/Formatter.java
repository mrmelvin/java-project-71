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

    public static String presentNullToString(Object inputData) {
        return inputData == null ? "null" : inputData.toString();
    }

    public static String formattedValuesForPlainOutput(Object inputData) {
        if (inputData instanceof ArrayList<?> | inputData instanceof Map<?, ?> | inputData instanceof Set<?>) {
            return "[complex value]";
        } else if (inputData == null) {
            return "null";
        } else if (inputData instanceof String) {
            return "'" + inputData + "'";
        } else {
            return inputData.toString();
        }
    }

    public static String getDataDefault(Map<String, Object[]> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[0].equals(1) & elem.getValue()[1].equals(1)) {
                if (Objects.equals(elem.getValue()[2], elem.getValue()[INDEX_SECOND_FILE_DATA])) {
                    output.append(PREFIX_EQUAL_DATA + elem.getKey());
                    output.append(": ");
                    output.append(presentNullToString(elem.getValue()[2]));
                    output.append("\n");
                } else {
                    output.append(PREFIX_DELETE_DATA + elem.getKey());
                    output.append(": ");
                    output.append(presentNullToString(elem.getValue()[2]));
                    output.append("\n");
                    output.append(PREFIX_ADD_DATA + elem.getKey());
                    output.append(": ");
                    output.append(presentNullToString(elem.getValue()[INDEX_SECOND_FILE_DATA]));
                    output.append("\n");
                }
            } else if (elem.getValue()[0].equals(0)) {
                output.append(PREFIX_ADD_DATA + elem.getKey());
                output.append(": ");
                output.append(presentNullToString(elem.getValue()[INDEX_SECOND_FILE_DATA]));
                output.append("\n");
            } else {
                output.append(PREFIX_DELETE_DATA + elem.getKey());
                output.append(": ");
                output.append(presentNullToString(elem.getValue()[2]));
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
                output.append("Property " + formattedValuesForPlainOutput(elem.getKey())
                        + " was added with value: "
                        + formattedValuesForPlainOutput(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            } else if (elem.getValue()[1].equals(0)) {
                output.append("Property " + formattedValuesForPlainOutput(elem.getKey()) + " was removed\n");
            } else if (!Objects.equals(elem.getValue()[2], elem.getValue()[INDEX_SECOND_FILE_DATA])) {
                output.append("Property " + formattedValuesForPlainOutput(elem.getKey()) + " was updated. From "
                            + formattedValuesForPlainOutput(elem.getValue()[2])
                            + " to " + formattedValuesForPlainOutput(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
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
                String[] changedValues = new String[]{presentNullToString(elem.getValue()[2]),
                        presentNullToString(elem.getValue()[INDEX_SECOND_FILE_DATA])};
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
            case DEFAULT_FORMAT:
                diffString = getDataDefault(inputData);
                break;
            case PLAIN_FORMAT:
                diffString = getDataPlain(inputData);
                break;
            case JSON_FORMAT:
                diffString = getDataJson(inputData);
                break;
            default:
                diffString = "Incorrect format type";
                break;
        }
        return diffString;
    }
}

package hexlet.code;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Formatter {

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static final int INDEX_SECOND_FILE_DATA = 3;

    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";

    public static String deletingNullPointerExceptions(Object inpitData) {
        return inpitData == null ? "null" : inpitData.toString();
    }

    public static String quotesNearValue(Object inpitData) {
        if (inpitData instanceof Arrays | inpitData instanceof Map<?,?> | inpitData instanceof Set<?>) {
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
                output.append("Property " + quotesNearValue(elem.getKey()) + " was removed\n");
            } else if (elem.getValue()[1].equals(0)) {
                output.append("Property " + quotesNearValue(elem.getKey())
                        + " was added with value: "
                        + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            } else {
                output.append("Property " + quotesNearValue(elem.getKey()) + " was updated. From "
                        + quotesNearValue(elem.getValue()[2])
                        + " to " + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            }
        }
        return output.toString();
    }

    public static String format(Map<String, Object[]> inputData, String currentFormat) {
        String diffString = "";
        if (currentFormat.equals(DEFAULT_FORMAT)) {
            diffString = getDataDefault(inputData);
        } else if (currentFormat.equals(PLAIN_FORMAT)) {
            diffString = getDataPlain(inputData);
        }
        return diffString;
    }
}

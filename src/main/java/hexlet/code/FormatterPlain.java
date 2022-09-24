package hexlet.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Objects;
import java.util.stream.Collectors;

public class FormatterPlain {
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


    public static String getDataPlain(Map<String, Object[]> inputData) {
        List<String> output = new ArrayList<>();
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[Formatter.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(0)) {
                output.add("Property " + formattedValuesForPlainOutput(elem.getKey())
                        + " was added with value: "
                        + formattedValuesForPlainOutput(elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA]));
            } else if (elem.getValue()[Formatter.INDEX_SECOND_FILE_AVAILABLE_KEY].equals(0)) {
                output.add("Property " + formattedValuesForPlainOutput(elem.getKey()) + " was removed");
            } else if (!Objects.equals(elem.getValue()[Formatter.INDEX_FIRST_FILE_DATA],
                    elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA])) {
                output.add("Property " + formattedValuesForPlainOutput(elem.getKey()) + " was updated. From "
                        + formattedValuesForPlainOutput(elem.getValue()[Formatter.INDEX_FIRST_FILE_DATA])
                        + " to " + formattedValuesForPlainOutput(elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA]));
            }
        }
        return output.stream().collect(Collectors.joining("\n"));
    }
}

package hexlet.code;

import java.util.Map;
import java.util.Objects;

public class FormatterStylish {

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static String getDataDefault(Map<String, Object[]> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[Formatter.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(1)
                    & elem.getValue()[Formatter.INDEX_SECOND_FILE_AVAILABLE_KEY].equals(1)) {
                if (Objects.equals(elem.getValue()[Formatter.INDEX_FIRST_FILE_DATA],
                                   elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA])) {
                    output.append(PREFIX_EQUAL_DATA + elem.getKey());
                    output.append(": ");
                    output.append(Formatter.presentNullToString(elem.getValue()[2]));
                    output.append("\n");
                } else {
                    output.append(PREFIX_DELETE_DATA + elem.getKey());
                    output.append(": ");
                    output.append(Formatter.presentNullToString(elem.getValue()[2]));
                    output.append("\n");
                    output.append(PREFIX_ADD_DATA + elem.getKey());
                    output.append(": ");
                    output.append(Formatter.presentNullToString(elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA]));
                    output.append("\n");
                }
            } else if (elem.getValue()[Formatter.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(0)) {
                output.append(PREFIX_ADD_DATA + elem.getKey());
                output.append(": ");
                output.append(Formatter.presentNullToString(elem.getValue()[Formatter.INDEX_SECOND_FILE_DATA]));
                output.append("\n");
            } else {
                output.append(PREFIX_DELETE_DATA + elem.getKey());
                output.append(": ");
                output.append(Formatter.presentNullToString(elem.getValue()[2]));
                output.append("\n");
            }
        }
        output.append("}");
        return output.toString();
    }
}

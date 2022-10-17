package hexlet.code.formatstyle;

import java.util.Map;
import java.util.Objects;
import static hexlet.code.formatstyle.FormatterDefault.presentNullToString;

public class FormatterStylish {

    public static final int INDEX_FIRST_FILE_AVAILABLE_KEY = 0;
    public static final int INDEX_SECOND_FILE_AVAILABLE_KEY = 1;
    public static final int INDEX_FIRST_FILE_DATA = 2;
    public static final int INDEX_SECOND_FILE_DATA = 3;

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static String getDataDefault(Map<String, Object[]> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            if (elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(1)
                    & elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_AVAILABLE_KEY].equals(1)) {
                if (Objects.equals(elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_DATA],
                                   elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA])) {
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
                    output.append(presentNullToString(elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA]));
                    output.append("\n");
                }
            } else if (elem.getValue()[FormatterDefault.INDEX_FIRST_FILE_AVAILABLE_KEY].equals(0)) {
                output.append(PREFIX_ADD_DATA + elem.getKey());
                output.append(": ");
                output.append(presentNullToString(elem.getValue()[FormatterDefault.INDEX_SECOND_FILE_DATA]));
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
}

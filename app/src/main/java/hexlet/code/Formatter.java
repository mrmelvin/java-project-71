package hexlet.code;

import java.util.Map;

public class Formatter {

    public static final int INDEX_FIRST_FILE_AVAILABLE_KEY = 0;
    public static final int INDEX_SECOND_FILE_AVAILABLE_KEY = 1;
    public static final int INDEX_FIRST_FILE_DATA = 2;
    public static final int INDEX_SECOND_FILE_DATA = 3;

    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";

    public static String presentNullToString(Object inputData) {
        return inputData == null ? "null" : inputData.toString();
    }

    public static String format(Map<String, Object[]> inputData, String currentFormat) {
        String diffString = "";
        switch (currentFormat) {
            case DEFAULT_FORMAT:
                diffString = FormatterStylish.getDataDefault(inputData);
                break;
            case PLAIN_FORMAT:
                diffString = FormatterPlain.getDataPlain(inputData);
                break;
            case JSON_FORMAT:
                diffString = FormatterJSON.getDataJson(inputData);
                break;
            default:
                diffString = "Incorrect format type";
                break;
        }
        return diffString;
    }
}

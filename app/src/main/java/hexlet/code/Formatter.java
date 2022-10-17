package hexlet.code;

import java.util.Map;
import static hexlet.code.formatstyle.FormatterStylish.getDataDefault;
import static hexlet.code.formatstyle.FormatterPlain.getDataPlain;
import static hexlet.code.formatstyle.FormatterJSON.getDataJson;

public class Formatter {


    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";


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

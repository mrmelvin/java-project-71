package hexlet.code.formatstyle;

import java.util.Map;
import static hexlet.code.formatstyle.SupportFormatter.presentNullToString;

public class FormatterStylish {

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static String getDataDefault(Map<String, Map<String, Object[]>> inputData) {
        StringBuilder output = new StringBuilder("{\n");

        for (var firstKey: inputData.entrySet()) {
            for (var changingElement : firstKey.getValue().entrySet()) {
                switch (changingElement.getKey()) {
                    case SupportFormatter.ADDED:
                        output.append(PREFIX_ADD_DATA + firstKey.getKey());
                        output.append(": ");
                        output.append(presentNullToString(changingElement.getValue()[0]));
                        output.append("\n");
                        break;
                    case SupportFormatter.DELETED:
                        output.append(PREFIX_DELETE_DATA + firstKey.getKey());
                        output.append(": ");
                        output.append(presentNullToString(changingElement.getValue()[0]));
                        output.append("\n");
                        break;
                    case SupportFormatter.CHANGED:
                        output.append(PREFIX_DELETE_DATA + firstKey.getKey());
                        output.append(": ");
                        output.append(presentNullToString(changingElement.getValue()[0]));
                        output.append("\n");
                        output.append(PREFIX_ADD_DATA + firstKey.getKey());
                        output.append(": ");
                        output.append(presentNullToString(changingElement.getValue()[1]));
                        output.append("\n");
                        break;
                    case SupportFormatter.NOCHANGED:
                        output.append(PREFIX_EQUAL_DATA + firstKey.getKey());
                        output.append(": ");
                        output.append(presentNullToString(changingElement.getValue()[0]));
                        output.append("\n");
                        break;
                    default:
                        continue;
                }
            }
        }
        output.append("}");
        return output.toString();
    }
}

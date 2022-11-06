package hexlet.code.formatstyle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    public static String getDataPlain(Map<String, Map<String, Object[]>> inputData) {
        List<String> output = new ArrayList<>();

        for (var outerElem: inputData.entrySet()) {
            for (var innerElem : outerElem.getValue().entrySet()) {
                switch (innerElem.getKey()) {
                    case SupportFormatter.ADDED:
                        output.add("Property "
                                + formattedValuesForPlainOutput(outerElem.getKey())
                                + " was added with value: "
                                + formattedValuesForPlainOutput(innerElem.getValue()[0]));
                        break;
                    case SupportFormatter.DELETED:
                        output.add("Property " + formattedValuesForPlainOutput(outerElem.getKey()) + " was removed");
                        break;
                    case SupportFormatter.CHANGED:
                        output.add("Property " + formattedValuesForPlainOutput(outerElem.getKey())
                                + " was updated. From "
                                + formattedValuesForPlainOutput(innerElem.getValue()[0])
                                + " to "
                                + formattedValuesForPlainOutput(innerElem.getValue()[1]));
                        break;
                    default:
                        continue;
                }
            }
        }
        return output.stream().collect(Collectors.joining("\n"));
    }
}

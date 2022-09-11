package hexlet.code;

import java.util.Map;
import java.util.Objects;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;



@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
                + "\n\tfilepath1\tpath to first file\n\tfilepath2\tpath to second file",
        version = "gendiff 0.1")

public class App implements Callable<Integer> {

    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";
    public static final int INDEX_SECOND_FILE_DATA = 3;

    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0")
    private String basicFile;

    @Parameters(index = "1")
    private String changedFile;


    public static String deletingNullPointerExceptions(Object inpitData) {
        return inpitData == null ? "null" : inpitData.toString();
    }

    public static String quotesNearValue(Object inpitData) {
        if (inpitData == null) {
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
                output.append("Property '" + elem.getKey() + "' was removed\n");
            } else if (elem.getValue()[1].equals(0)) {
                output.append("Property '" + elem.getKey()
                        + "' was added with value: "
                        + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            } else {
                output.append("Property '" + elem.getKey() + "' was updated. From "
                        + quotesNearValue(elem.getValue()[2])
                        + " to " + quotesNearValue(elem.getValue()[INDEX_SECOND_FILE_DATA]) + "\n");
            }
        }
        return output.toString();
    }


    /**
     *
     * @return exitState code
     * @throws Exception
     */
    public Integer call() throws Exception {
        Map<String, Object[]> differrent = Parser.parse(basicFile, changedFile);
        System.out.println(getDataDefault(differrent));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

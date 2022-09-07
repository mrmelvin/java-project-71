package hexlet.code;

import java.util.Map;

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

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private boolean isFormat;

    @Parameters(index = "0")
    private String basicFile;

    @Parameters(index = "1")
    private String changedFile;



    public static String getData(Map<String, Object> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            String value = elem.getValue() == null ? "null" : elem.getValue().toString();
            output.append(elem.getKey());
            output.append(": ");
            output.append(value);
            output.append("\n");
        }
        output.append("}");
        return output.toString();
    }

    /**
     *
     * @return exitState code
     * @throws Exception
     */
    public Integer call() throws Exception {
        Map<String, Object> differrent = Parser.parse(basicFile, changedFile);
        System.out.println(getData(differrent));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

package hexlet.code;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import com.fasterxml.jackson.databind.ObjectMapper;

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

    /**
     * This method compare two files.
     * @param firstPath
     * @param secondPath
     * @return sorted Map
     * @throws IOException
     */

    public static final String IDENTIFICATOR_SAME_DATA = "    ";
    public static final String IDENTIFICATOR_DELETE_DATA = "  - ";
    public static final String IDENTIFICATOR_ADD_DATA = "  + ";
    public static Map<String, Object> parseTwoFiles(String firstPath, String secondPath) throws IOException {


        Path pathToBasicFile = Paths.get(firstPath).isAbsolute() ? Paths.get(firstPath)
                                                                 : Paths.get(firstPath).toAbsolutePath();
        Path pathToChangedFile = Paths.get(secondPath).isAbsolute() ? Paths.get(secondPath)
                                                                    : Paths.get(secondPath).toAbsolutePath();
        Map<String, Object> result = new LinkedHashMap<>();

        if (Files.exists(pathToBasicFile) & Files.exists(pathToChangedFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> mapBasicFile = objectMapper.readValue(new File(pathToBasicFile.toString()), Map.class);
            Map<String, Object> mapChangedFile = objectMapper.readValue(new File(pathToChangedFile.toString()),
                                                                        Map.class);

            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(mapBasicFile.keySet());
            allKeys.addAll(mapChangedFile.keySet());
            for (String elem: allKeys) {
                if (mapBasicFile.containsKey(elem) & mapChangedFile.containsKey(elem)) {
                    if (mapBasicFile.getOrDefault(elem, "key is null")
                            .equals(mapChangedFile.getOrDefault(elem, "key is null"))) {
                        result.put(IDENTIFICATOR_SAME_DATA + elem, mapBasicFile.getOrDefault(elem, null));
                    } else {
                        result.put(IDENTIFICATOR_DELETE_DATA + elem, mapBasicFile.get(elem));
                        result.put(IDENTIFICATOR_ADD_DATA + elem, mapChangedFile.get(elem));
                    }
                } else if (mapChangedFile.containsKey(elem) & !mapBasicFile.containsKey(elem)) {
                    result.put(IDENTIFICATOR_ADD_DATA + elem, mapChangedFile.get(elem));
                } else {
                    result.put(IDENTIFICATOR_DELETE_DATA + elem, mapBasicFile.get(elem));
                }
            }
        }
        return result;
    }

    public static String getData(Map<String, Object> inputData) {
        StringBuilder output = new StringBuilder("{\n");
        for (var elem: inputData.entrySet()) {
            output.append(elem.getKey());
            output.append(": ");
            output.append(elem.getValue());
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
        Map<String, Object> differrent = parseTwoFiles(basicFile, changedFile);
        System.out.println(getData(differrent));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

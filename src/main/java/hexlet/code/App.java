package hexlet.code;

import java.io.IOException;
import java.util.*;
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


//final GLOBAL_DESCRIPTION = "Compares two configuration files and shows a difference.         filepath1         path to first file        filepath2         path to second file";
@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference.\n\tfilepath1\tpath to first file\n\tfilepath2\tpath to second file",
        version = "gendiff 0.1")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    boolean isFormat;

    @Parameters(index = "0")
    String pathToBasicFile;

    @Parameters(index = "1")
    String pathToChangedFile;

    public Map<String, Object> parseTwoFiles(String firstPath, String secondPath) throws IOException {

        Path pathToBasicFile = Paths.get(firstPath).isAbsolute() ? Paths.get(firstPath) : Paths.get(firstPath).toAbsolutePath();
        Path pathToChangedFile = Paths.get(secondPath).isAbsolute() ? Paths.get(secondPath) : Paths.get(secondPath).toAbsolutePath();
        Map<String, Object> result = new TreeMap<>();

        if (Files.exists(pathToBasicFile) & Files.exists(pathToChangedFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> mapBasicFile = objectMapper.readValue(new File(pathToBasicFile.toString()), Map.class);
            Map<String, Object> mapChangedFile = objectMapper.readValue(new File(pathToChangedFile.toString()), Map.class);

            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(mapBasicFile.keySet());
            allKeys.addAll(mapChangedFile.keySet());
            for (String elem: allKeys) {
                if (mapBasicFile.containsKey(elem) & mapChangedFile.containsKey(elem)) {
                    if (mapBasicFile.getOrDefault(elem, "key is null").equals(mapChangedFile.getOrDefault(elem, "key is null"))) {
                        result.put(elem, mapBasicFile.getOrDefault(elem, null));
                    } else {
                        result.put(elem + "-", mapBasicFile.get(elem));
                        result.put(elem + "+", mapChangedFile.get(elem));
                    }
                } else if (mapChangedFile.containsKey(elem) & !mapBasicFile.containsKey(elem)) {
                    result.put(elem + "+", mapChangedFile.get(elem));
                } else {
                    result.put(elem + "-", mapBasicFile.get(elem));
                }
            }
        }
        return result;
    }
    public Integer call() throws Exception {
        Map<String, Object> differrent = parseTwoFiles(pathToBasicFile, pathToChangedFile);
        for (var diffElement: differrent.entrySet()) {
            String key = diffElement.getKey();
            if (key.endsWith("+") | key.endsWith("-")) {
                key = key.substring(key.length() - 1) + " " + key.substring(0, key.length() - 1);
            }
            System.out.println(key + ": "+ diffElement.getValue());
        }
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

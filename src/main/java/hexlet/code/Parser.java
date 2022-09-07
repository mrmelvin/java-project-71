package hexlet.code;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static final String PREFIX_EQUAL_DATA = "    ";
    public static final String PREFIX_DELETE_DATA = "  - ";
    public static final String PREFIX_ADD_DATA = "  + ";


    public static Map<String, Object> parse(String firstPath, String secondPath) throws IOException {
        Map<String, Object> output = new LinkedHashMap<>();
        if (firstPath.endsWith("json") & secondPath.endsWith("json")) {
            output = parseTwoFilesJSON(firstPath, secondPath);
        } else if (firstPath.endsWith("yaml") & secondPath.endsWith("yaml")) {
            output = parseTwoFilesYAML(firstPath, secondPath);
        } else {
            System.out.println("Files have two different format");
        }
        return output;
    }

    /**
     * This method compare two files.
     * @param firstPath
     * @param secondPath
     * @return sorted Map
     * @throws IOException
     */
    public static Map<String, Object> parseTwoFilesJSON(String firstPath, String secondPath) throws IOException {


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
                    if (Objects.equals(mapBasicFile.getOrDefault(elem, "key is null"),
                                       mapChangedFile.getOrDefault(elem, "key is null"))) {
                        result.put(PREFIX_EQUAL_DATA + elem, mapBasicFile.getOrDefault(elem, "key is null"));
                    } else {
                        result.put(PREFIX_DELETE_DATA + elem, mapBasicFile.get(elem));
                        result.put(PREFIX_ADD_DATA + elem, mapChangedFile.get(elem));
                    }
                } else if (mapChangedFile.containsKey(elem) & !mapBasicFile.containsKey(elem)) {
                    result.put(PREFIX_ADD_DATA + elem, mapChangedFile.get(elem));
                } else {
                    result.put(PREFIX_DELETE_DATA + elem, mapBasicFile.get(elem));
                }
            }
        }
        return result;
    }

    public static Map<String, Object> parseTwoFilesYAML(String firstPath, String secondPath) throws IOException {


        Path pathToBasicFile = Paths.get(firstPath).isAbsolute() ? Paths.get(firstPath)
                : Paths.get(firstPath).toAbsolutePath();
        Path pathToChangedFile = Paths.get(secondPath).isAbsolute() ? Paths.get(secondPath)
                : Paths.get(secondPath).toAbsolutePath();
        Map<String, Object> result = new LinkedHashMap<>();

        if (Files.exists(pathToBasicFile) & Files.exists(pathToChangedFile)) {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            Map<String, Object> mapBasicFile = objectMapper.readValue(new File(pathToBasicFile.toString()), Map.class);
            Map<String, Object> mapChangedFile = objectMapper.readValue(new File(pathToChangedFile.toString()),
                    Map.class);

            Set<String> allKeys = new TreeSet<>();
            allKeys.addAll(mapBasicFile.keySet());
            allKeys.addAll(mapChangedFile.keySet());
            for (String elem: allKeys) {
                if (mapBasicFile.containsKey(elem) & mapChangedFile.containsKey(elem)) {
                    if (Objects.equals(mapBasicFile.getOrDefault(elem, "key is null"),
                            mapChangedFile.getOrDefault(elem, "key is null"))) {
                        result.put(PREFIX_EQUAL_DATA + elem, mapBasicFile.getOrDefault(elem, "key is null"));
                    } else {
                        result.put(PREFIX_DELETE_DATA + elem, mapBasicFile.get(elem));
                        result.put(PREFIX_ADD_DATA + elem, mapChangedFile.get(elem));
                    }
                } else if (mapChangedFile.containsKey(elem) & !mapBasicFile.containsKey(elem)) {
                    result.put(PREFIX_ADD_DATA + elem, mapChangedFile.get(elem));
                } else {
                    result.put(PREFIX_DELETE_DATA + elem, mapBasicFile.get(elem));
                }
            }
        }
        return result;
    }
}

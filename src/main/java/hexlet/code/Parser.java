package hexlet.code;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.TreeSet;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {


    public static Map<String, Object[]> parse(String firstPath, String secondPath) throws IOException {
        Map<String, Object[]> output = new LinkedHashMap<>();
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
     * Статический метод сравнивающий два json файла и возвращающий в виде отображения Map<String, Object[]>.
     * @param firstPath Путь до первого файла
     * @param secondPath Путь до второго файла
     * @return result Отображение. Представляет собой отображание вида Map<String, Object[]>, где
     *                  String - ключ параметров из двух файлов;
     *                  Object[] - массив из 4 параметров:
     *                      первый параметр отвечает за наличие ключа в первом файле (0 - отсутствие, 1 - наличие),
     *                      второй параметр отвечает за наличие ключа во втором файле (0 - отсутствие, 1 - наличие),
     *                      третий параметр - значение ключа в первом файле (если значения нет выставляется null),
     *                      четвёртый параметр - значение ключа во втором файле (если значения нет выставляется null).
     *                 Возможный вид Map<"key2", [0, 1, null, "foo"]> -
     *                                                              во втором файле добавилось значение {"key2": "foo"}
     *
     * @throws IOException
     */
    public static Map<String, Object[]> parseTwoFilesJSON(String firstPath, String secondPath) throws IOException {


        Path pathToBasicFile = Paths.get(firstPath).isAbsolute() ? Paths.get(firstPath)
                : Paths.get(firstPath).toAbsolutePath();
        Path pathToChangedFile = Paths.get(secondPath).isAbsolute() ? Paths.get(secondPath)
                : Paths.get(secondPath).toAbsolutePath();

        Map<String, Object[]> result = new LinkedHashMap<>();

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
                    result.put(elem, new Object[]{1, 1, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                } else if (mapBasicFile.containsKey(elem) & !mapChangedFile.containsKey(elem)) {
                    result.put(elem, new Object[]{1, 0, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                } else {
                    result.put(elem, new Object[]{0, 1, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                }
            }
        }
        return result;
    }

    public static Map<String, Object[]> parseTwoFilesYAML(String firstPath, String secondPath) throws IOException {


        Path pathToBasicFile = Paths.get(firstPath).isAbsolute() ? Paths.get(firstPath)
                : Paths.get(firstPath).toAbsolutePath();
        Path pathToChangedFile = Paths.get(secondPath).isAbsolute() ? Paths.get(secondPath)
                : Paths.get(secondPath).toAbsolutePath();
        Map<String, Object[]> result = new LinkedHashMap<>();

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
                    result.put(elem, new Object[]{1, 1, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                } else if (mapBasicFile.containsKey(elem) & !mapChangedFile.containsKey(elem)) {
                    result.put(elem, new Object[]{1, 0, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                } else {
                    result.put(elem, new Object[]{0, 1, mapBasicFile.getOrDefault(elem, null),
                                                        mapChangedFile.getOrDefault(elem, null)});
                }
            }
        }
        return result;
    }

}

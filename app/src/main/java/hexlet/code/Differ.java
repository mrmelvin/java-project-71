package hexlet.code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Differ {

    public static String getFileExtension(Path path) {
        return path.toString().substring(path.toString().lastIndexOf(".") + 1);
    }
    public static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).isAbsolute() ? Paths.get(filePath) : Paths.get(filePath).toAbsolutePath();
    }

    public static String readingFileToString(String pathToFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }
    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Path fullPathToFirstFile = getAbsolutePath(filePath1);
        Path fullPathToSecondFile = getAbsolutePath(filePath2);

        String firstFileExtension = getFileExtension(fullPathToFirstFile);
        String secondFileExtension = getFileExtension(fullPathToSecondFile);

        String contentFirstFile = readingFileToString(fullPathToFirstFile.toString());
        String contentSecondFile = readingFileToString(fullPathToSecondFile.toString());

        Map<String, String> firstParsingMap = Parser.parse(contentFirstFile, firstFileExtension);
        Map<String, String> secondParsingMap = Parser.parse(contentSecondFile, secondFileExtension);

        Map<String, Map<String, Object[]>> preFormatted = DifferBuilder.differBuilder(firstParsingMap,
                                                                                      secondParsingMap);
        return Formatter.format(preFormatted, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return Differ.generate(filePath1, filePath2, "stylish");
    }
}

package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;


public class Differ {

    public static String getFileExtension(Path path) {
        return path.toString().substring(path.toString().lastIndexOf(".") + 1);
    }
    public static Path getAbsolutePath(String filePath) {
        return Paths.get(filePath).isAbsolute() ? Paths.get(filePath) : Paths.get(filePath).toAbsolutePath();
    }

    public static String generate(String filePath1, String filePath2, String formatName) throws IOException {
        Path fullPathToFirstFile = getAbsolutePath(filePath1);
        Path fullPathToSecondFile = getAbsolutePath(filePath2);

        String firstFileExtension = getFileExtension(fullPathToFirstFile);
        String secondFileExtension = getFileExtension(fullPathToSecondFile);

        Map<String, String> firstParsingMap = Parser.parse(fullPathToFirstFile, firstFileExtension);
        Map<String, String> secondParsingMap = Parser.parse(fullPathToSecondFile, secondFileExtension);

        Map<String, Object[]> preFormatted = DifferBuilder.differBuilder(firstParsingMap, secondParsingMap);
        return Formatter.format(preFormatted, formatName);
    }

    public static String generate(String filePath1, String filePath2) throws IOException {
        return Differ.generate(filePath1, filePath2, "stylish");
    }

}

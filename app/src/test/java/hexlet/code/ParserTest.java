package hexlet.code;


import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.stream.Collectors;

public class ParserTest {

    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";

    public static final String JSON_OUTPUT = "src/test/resources/expectedfiles/expectedJSONOutput.json";
    public static final String SIMPLE_JSON_OUTPUT = "src/test/resources/expectedfiles/expectedSimpleJSONOutput.json";
    public static final String PLAIN = "src/test/resources/expectedfiles/expectedPlain.txt";

    public static final String NESTED_FILES = "src/test/resources/expectedfiles/expectedNestedFiles.txt";
    public static final String TWO_IDENTICAL_FILES = "src/test/resources/expectedfiles/expectedTwoIdenticalFiles.txt";
    public static final String TWO_DIFFERENT_FILES_WITH_SAME_KEYS =
                                    "src/test/resources/expectedfiles/expectedTwoDifferentFilesWithSameKeys.txt";
    public static final String TWO_DIFFERENT_FILES = "src/test/resources/expectedfiles/expectedTwoDifferentFiles.txt";


    public static String readingFileToString(String pathToFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(pathToFile)));
        return reader.lines().collect(Collectors.joining(System.lineSeparator()));
    }

    @Test
    public void testNestedJSONOutput() throws IOException {

        String expectedJSONOutput = readingFileToString(JSON_OUTPUT);

        var actualJSONOutput = Differ.generate("src/test/resources/filestodiff/nestedFile1.json",
                "src/test/resources/filestodiff/nestedFile2.json",
                JSON_FORMAT);
        assertEquals(expectedJSONOutput, actualJSONOutput);
    }

    @Test
    public void testSimpleJSONOutput() throws IOException {
        String expectedSimpleJSONOutput = readingFileToString(SIMPLE_JSON_OUTPUT);
        var actualSimpleJSONOutput = Differ.generate("src/test/resources/filestodiff/fileWithSameKeys1.json",
                "src/test/resources/filestodiff/fileWithSameKeys2.json",
                JSON_FORMAT);
        assertEquals(expectedSimpleJSONOutput, actualSimpleJSONOutput);
    }

    @Test
    public void testPlainJSON() throws Exception {

        String expectedPlainJSON = readingFileToString(PLAIN);

        var actualPlainJSON = Differ.generate("src/test/resources/filestodiff/nestedFile1.json",
                                            "src/test/resources/filestodiff/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainJSON, actualPlainJSON);
    }

    @Test
    public void testPlainYAML() throws Exception {

        String expectedPlainYAML = readingFileToString(PLAIN);
        var actualPlainYAML = Differ.generate("src/test/resources/filestodiff/nestedFile1.json",
                                            "src/test/resources/filestodiff/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainYAML, actualPlainYAML);
    }

    @Test
    public void testNestedFilesJSON() throws Exception {

        String expectedNestedFilesJSON = readingFileToString(NESTED_FILES);

        var actualNestedFilesJSON = Differ.generate("src/test/resources/filestodiff/nestedFile1.json",
                                                    "src/test/resources/filestodiff/nestedFile2.json",
                                                            DEFAULT_FORMAT);
        assertEquals(expectedNestedFilesJSON, actualNestedFilesJSON);
    }

    @Test
    public void testNestedFilesYAML() throws Exception {

        String expectedNestedFilesYAML = readingFileToString(NESTED_FILES);

        var actualNestedFilesYAML = Differ.generate("src/test/resources/filestodiff/nestedFile1.yml",
                                                    "src/test/resources/filestodiff/nestedFile2.yml",
                                                            DEFAULT_FORMAT);
        assertEquals(expectedNestedFilesYAML, actualNestedFilesYAML);
    }
    @Test
    public void testTwoIdenticalFilesJSON() throws Exception {
        String expectedTwoIdenticalFiles = readingFileToString(TWO_IDENTICAL_FILES);
        var actualTwoIdenticalFilesJSON = Differ.generate("src/test/resources/filestodiff/twoIdenticalFile1.json",
                                                        "src/test/resources/filestodiff/twoIdenticalFile2.json",
                                                                DEFAULT_FORMAT);
        assertEquals(expectedTwoIdenticalFiles, actualTwoIdenticalFilesJSON);

    }

    @Test
    public void testTwoIdenticalFilesYAML() throws Exception {
        String expectedTwoIdenticalFiles = readingFileToString(TWO_IDENTICAL_FILES);

        var actualTwoIdenticalFilesYAML = Differ.generate("src/test/resources/filestodiff/twoIdenticalFile1.yml",
                "src/test/resources/filestodiff/twoIdenticalFile2.yml", DEFAULT_FORMAT);
        assertEquals(expectedTwoIdenticalFiles, actualTwoIdenticalFilesYAML);

    }


    @Test
    public void testTwoDifferentFilesWithSameKeysJSON() throws IOException {
        String expectedTwoDifferentFilesWithSameKeys = readingFileToString(TWO_DIFFERENT_FILES_WITH_SAME_KEYS);
        var actualTwoDifferentFilesWithSameKeysJSON =
                Differ.generate("src/test/resources/filestodiff/fileWithSameKeys1.json",
                        "src/test/resources/filestodiff/fileWithSameKeys2.json",
                                DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFilesWithSameKeys, actualTwoDifferentFilesWithSameKeysJSON);
    }

    @Test
    public void testTwoDifferentFilesWithSameKeysYAML() throws IOException {
        String expectedTwoDifferentFilesWithSameKeys = readingFileToString(TWO_DIFFERENT_FILES_WITH_SAME_KEYS);
        var actualTwoDifferentFilesWithSameKeysYAML =
                Differ.generate("src/test/resources/filestodiff/fileWithSameKeys1.yml",
                "src/test/resources/filestodiff/fileWithSameKeys2.yml",
                        DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFilesWithSameKeys, actualTwoDifferentFilesWithSameKeysYAML);
    }

    @Test
    public void testTwoDifferentFilesJSON() throws IOException {
        String expectedTwoDifferentFiles = readingFileToString(TWO_DIFFERENT_FILES);

        var actualTwoDifferentFilesJSON = Differ.generate("src/test/resources/filestodiff/differentFile1.json",
                "src/test/resources/filestodiff/differentFile2.json", DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesJSON);
    }

    @Test
    public void testTwoDifferentFilesYAML() throws IOException {
        String expectedTwoDifferentFiles = readingFileToString(TWO_DIFFERENT_FILES);

        var actualTwoDifferentFilesYAML = Differ.generate("src/test/resources/filestodiff/differentFile1.yml",
                                                        "src/test/resources/filestodiff/differentFile2.yml",
                                                                DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesYAML);
    }

    @Test
    public void testTwoDifferentFilesDefaultFormat() throws IOException {
        String expectedTwoDifferentFiles = readingFileToString(TWO_DIFFERENT_FILES);
        var actualTwoDifferentFilesJSON = Differ.generate("src/test/resources/filestodiff/differentFile1.json",
                "src/test/resources/filestodiff/differentFile2.json");
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesJSON);
    }
}

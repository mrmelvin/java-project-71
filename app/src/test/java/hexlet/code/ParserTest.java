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

    @Test
    public void testNestedJSONOutput() throws IOException {

        String jsonOutput = "src/test/resources/expectedJSONOutput.json";
        BufferedReader reader = new BufferedReader(new FileReader(new File(jsonOutput)));
        String expectedJSONOutput = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        var actualJSONOutput = Differ.generate("src/test/resources/nestedFile1.json",
                "src/test/resources/nestedFile2.json",
                JSON_FORMAT);
        assertEquals(expectedJSONOutput, actualJSONOutput);
    }

    @Test
    public void testSimpleJSONOutput() throws IOException {
        String expectedSimpleJSONOutput = "{\"added\":{},\"deleted\":{\"location\":\"Ocean\"},"
                + "\"changed\":{\"first_name\":\"[Sammy, Lenny]\",\"followers\":\"[987, 876]\","
                + "\"online\":\"[true, false]\"}}";
        var actualSimpleJSONOutput = Differ.generate("src/test/resources/fileWithSameKeys1.json",
                "src/test/resources/fileWithSameKeys2.json",
                JSON_FORMAT);
        assertEquals(expectedSimpleJSONOutput, actualSimpleJSONOutput);
    }

    @Test
    public void testPlainJSON() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/expectedPlain.txt")));
        String expectedPlainJSON = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        var actualPlainJSON = Differ.generate("src/test/resources/nestedFile1.json",
                                            "src/test/resources/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainJSON, actualPlainJSON);
    }

    @Test
    public void testPlainYAML() throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/expectedPlain.txt")));
        String expectedPlainYAML = reader.lines().collect(Collectors.joining(System.lineSeparator()));
        var actualPlainYAML = Differ.generate("src/test/resources/nestedFile1.json",
                                            "src/test/resources/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainYAML, actualPlainYAML);
    }

    @Test
    public void testNestedFilesJSON() throws Exception {

        String nestedFile = "src/test/resources/expectedNestedFiles.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(nestedFile)));
        String expectedNestedFilesJSON = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        var actualNestedFilesJSON = Differ.generate("src/test/resources/nestedFile1.json",
                                                    "src/test/resources/nestedFile2.json",
                                                            DEFAULT_FORMAT);
        assertEquals(expectedNestedFilesJSON, actualNestedFilesJSON);
    }

    @Test
    public void testNestedFilesYAML() throws Exception {

        String nestedFile2 = "src/test/resources/expectedNestedFiles.txt";
        BufferedReader reader = new BufferedReader(new FileReader(new File(nestedFile2)));
        String expectedNestedFilesYAML = reader.lines().collect(Collectors.joining(System.lineSeparator()));

        var actualNestedFilesYAML = Differ.generate("src/test/resources/nestedFile1.yml",
                                                    "src/test/resources/nestedFile2.yml",
                                                            DEFAULT_FORMAT);
        assertEquals(expectedNestedFilesYAML, actualNestedFilesYAML);
    }
    @Test
    public void testTwoIdenticalFilesJSON() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actualTwoIdenticalFilesJSON = Differ.generate("src/test/resources/twoIdenticalFile1.json",
                                                        "src/test/resources/twoIdenticalFile2.json",
                                                                DEFAULT_FORMAT);
        assertEquals(expectedTwoIdenticalFiles, actualTwoIdenticalFilesJSON);

    }

    @Test
    public void testTwoIdenticalFilesYAML() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actualTwoIdenticalFilesYAML = Differ.generate("src/test/resources/twoIdenticalFile1.yml",
                "src/test/resources/twoIdenticalFile2.yml", DEFAULT_FORMAT);
        assertEquals(expectedTwoIdenticalFiles, actualTwoIdenticalFilesYAML);

    }


    @Test
    public void testTwoDifferentFilesWithSameKeysJSON() throws IOException {
        String expectedTwoDifferentFilesWithSameKeys = "{\n"
                + "  - first_name: Sammy\n"
                + "  + first_name: Lenny\n"
                + "  - followers: 987\n"
                + "  + followers: 876\n"
                + "    last_name: Shark\n"
                + "  - location: Ocean\n"
                + "  - online: true\n"
                + "  + online: false"
                + "\n}";
        var actualTwoDifferentFilesWithSameKeysJSON =
                Differ.generate("src/test/resources/fileWithSameKeys1.json",
                        "src/test/resources/fileWithSameKeys2.json",
                                DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFilesWithSameKeys, actualTwoDifferentFilesWithSameKeysJSON);
    }

    @Test
    public void testTwoDifferentFilesWithSameKeysYAML() throws IOException {
        String expectedTwoDifferentFilesWithSameKeys = "{\n"
                + "  - first_name: Sammy\n"
                + "  + first_name: Lenny\n"
                + "  - followers: 987\n"
                + "  + followers: 876\n"
                + "    last_name: Shark\n"
                + "  - location: Ocean\n"
                + "  - online: true\n"
                + "  + online: false"
                + "\n}";
        var actualTwoDifferentFilesWithSameKeysYAML =
                Differ.generate("src/test/resources/fileWithSameKeys1.yml",
                "src/test/resources/fileWithSameKeys2.yml",
                        DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFilesWithSameKeys, actualTwoDifferentFilesWithSameKeysYAML);
    }

    @Test
    public void testTwoDifferentFilesJSON() throws IOException {
        String expectedTwoDifferentFiles = "{\n"
                + "  - age: 30\n"
                + "  + director: Francis Ford Coppola\n"
                + "  + filmName: Godfather\n"
                + "  - firstName: John\n"
                + "  - lastName: Doe\n"
                + "  + year: 1972"
                + "\n}";


        var actualTwoDifferentFilesJSON = Differ.generate("src/test/resources/differentFile1.json",
                "src/test/resources/differentFile2.json", DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesJSON);
    }

    @Test
    public void testTwoDifferentFilesYAML() throws IOException {
        String expectedTwoDifferentFiles = "{\n"
                + "  - age: 30\n"
                + "  + director: Francis Ford Coppola\n"
                + "  + filmName: Godfather\n"
                + "  - firstName: John\n"
                + "  - lastName: Doe\n"
                + "  + year: 1972"
                + "\n}";


        var actualTwoDifferentFilesYAML = Differ.generate("src/test/resources/differentFile1.yml",
                                                        "src/test/resources/differentFile2.yml",
                                                                DEFAULT_FORMAT);
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesYAML);
    }

    @Test
    public void testTwoDifferentFilesDefaultFormat() throws IOException {
        String expectedTwoDifferentFiles = "{\n"
                + "  - age: 30\n"
                + "  + director: Francis Ford Coppola\n"
                + "  + filmName: Godfather\n"
                + "  - firstName: John\n"
                + "  - lastName: Doe\n"
                + "  + year: 1972"
                + "\n}";


        var actualTwoDifferentFilesJSON = Differ.generate("src/test/resources/differentFile1.json",
                "src/test/resources/differentFile2.json");
        assertEquals(expectedTwoDifferentFiles, actualTwoDifferentFilesJSON);
    }
}

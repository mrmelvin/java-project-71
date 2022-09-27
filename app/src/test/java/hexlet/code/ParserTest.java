package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    public static final String DEFAULT_FORMAT = "stylish";
    public static final String PLAIN_FORMAT = "plain";
    public static final String JSON_FORMAT = "json";


    @Test
    public void testNestedJSONOutput() throws IOException {
        String expectedJSONOutput = "{\"added\":{\"key2\":\"value2\",\"numbers4\":\"[4, 5, 6]\","
                + "\"obj1\":\"{nestedKey=value, isNested=true}\"},\"deleted\":{\"key1\":\"value1\","
                + "\"numbers3\":\"[3, 4, 5]\"},\"changed\":{\"chars2\":\"[[d, e, f], false]\","
                + "\"checked\":\"[false, true]\",\"default\":\"[null, [value1, value2]]\",\"id\":\"[45, null]\","
                + "\"numbers2\":\"[[2, 3, 4, 5], [22, 33, 44, 55]]\",\"setting1\":\"[Some value, Another value]\","
                + "\"setting2\":\"[200, 300]\",\"setting3\":\"[true, none]\"}}";
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
        String expectedPlainJSON = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";

        var actualPlainJSON = Differ.generate("src/test/resources/nestedFile1.json",
                                            "src/test/resources/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainJSON, actualPlainJSON);
    }

    @Test
    public void testPlainYAML() throws Exception {
        String expectedPlainYAML = "Property 'chars2' was updated. From [complex value] to false\n"
                + "Property 'checked' was updated. From false to true\n"
                + "Property 'default' was updated. From null to [complex value]\n"
                + "Property 'id' was updated. From 45 to null\n"
                + "Property 'key1' was removed\n"
                + "Property 'key2' was added with value: 'value2'\n"
                + "Property 'numbers2' was updated. From [complex value] to [complex value]\n"
                + "Property 'numbers3' was removed\n"
                + "Property 'numbers4' was added with value: [complex value]\n"
                + "Property 'obj1' was added with value: [complex value]\n"
                + "Property 'setting1' was updated. From 'Some value' to 'Another value'\n"
                + "Property 'setting2' was updated. From 200 to 300\n"
                + "Property 'setting3' was updated. From true to 'none'";
        var actualPlainYAML = Differ.generate("src/test/resources/nestedFile1.json",
                                            "src/test/resources/nestedFile2.json",
                                                    PLAIN_FORMAT);
        assertEquals(expectedPlainYAML, actualPlainYAML);
    }

    @Test
    public void testNestedFilesJSON() throws Exception {
        String expectedNestedFilesJSON = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none"
                + "\n}";
        var actualNestedFilesJSON = Differ.generate("src/test/resources/nestedFile1.json",
                                                    "src/test/resources/nestedFile2.json",
                                                            DEFAULT_FORMAT);
        assertEquals(expectedNestedFilesJSON, actualNestedFilesJSON);
    }

    @Test
    public void testNestedFilesYAML() throws Exception {
        String expectedNestedFilesYAML = "{\n"
                + "    chars1: [a, b, c]\n"
                + "  - chars2: [d, e, f]\n"
                + "  + chars2: false\n"
                + "  - checked: false\n"
                + "  + checked: true\n"
                + "  - default: null\n"
                + "  + default: [value1, value2]\n"
                + "  - id: 45\n"
                + "  + id: null\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "    numbers1: [1, 2, 3, 4]\n"
                + "  - numbers2: [2, 3, 4, 5]\n"
                + "  + numbers2: [22, 33, 44, 55]\n"
                + "  - numbers3: [3, 4, 5]\n"
                + "  + numbers4: [4, 5, 6]\n"
                + "  + obj1: {nestedKey=value, isNested=true}\n"
                + "  - setting1: Some value\n"
                + "  + setting1: Another value\n"
                + "  - setting2: 200\n"
                + "  + setting2: 300\n"
                + "  - setting3: true\n"
                + "  + setting3: none"
                + "\n}";
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

package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

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
        var actualNestedFilesJSON = Parser.parse("src/test/resources/nestedFile1.json",
                "src/test/resources/nestedFile2.json");
        assertEquals(expectedNestedFilesJSON, App.getData(actualNestedFilesJSON));
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
        var actualNestedFilesYAML = Parser.parse("src/test/resources/nestedFile1.yaml",
                "src/test/resources/nestedFile2.yaml");
        assertEquals(expectedNestedFilesYAML, App.getData(actualNestedFilesYAML));
    }
    @Test
    public void testTwoIdenticalFilesJSON() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actualTwoIdenticalFilesJSON = Parser.parse("src/test/resources/twoIdenticalFile1.json",
                "src/test/resources/twoIdenticalFile2.json");
        assertEquals(expectedTwoIdenticalFiles, App.getData(actualTwoIdenticalFilesJSON));

    }

    @Test
    public void testTwoIdenticalFilesYAML() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actualTwoIdenticalFilesYAML = Parser.parse("src/test/resources/twoIdenticalFile1.yaml",
                "src/test/resources/twoIdenticalFile2.yaml");
        assertEquals(expectedTwoIdenticalFiles, App.getData(actualTwoIdenticalFilesYAML));

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
        var actualTwoDifferentFilesWithSameKeysJSON = Parser.parse("src/test/resources/fileWithSameKeys1.json",
                "src/test/resources/fileWithSameKeys2.json");
        assertEquals(expectedTwoDifferentFilesWithSameKeys, App.getData(actualTwoDifferentFilesWithSameKeysJSON));
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
        var actualTwoDifferentFilesWithSameKeysYAML = Parser.parse("src/test/resources/fileWithSameKeys1.yaml",
                "src/test/resources/fileWithSameKeys2.yaml");
        assertEquals(expectedTwoDifferentFilesWithSameKeys, App.getData(actualTwoDifferentFilesWithSameKeysYAML));
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


        var actualTwoDifferentFilesJSON = Parser.parse("src/test/resources/differentFile1.json",
                "src/test/resources/differentFile2.json");
        assertEquals(expectedTwoDifferentFiles, App.getData(actualTwoDifferentFilesJSON));
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


        var actualTwoDifferentFilesYAML = Parser.parse("src/test/resources/differentFile1.yaml",
                "src/test/resources/differentFile2.yaml");
        assertEquals(expectedTwoDifferentFiles, App.getData(actualTwoDifferentFilesYAML));
    }
}

package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testNestedFilesJSON() throws Exception {
        String expectedNestedFilesJSON = "{\n"
                + "  + chars1: [a, b, c]"
                + "  - chars2: [d, e, f]"
                + "  + chars2: false"
                + "  - checked: false"
                + "  + checked: true"
//                + "  - default: null"
//                + "  + default: [value1, value2]"
//                + "  - id: 45"
//                + "  + id: null"
                + "  - key1: value1"
                + "  + key2: value2"
                + "  numbers1: [1, 2, 3, 4]"
                + "  - numbers2: [2, 3, 4, 5]"
                + "  + numbers2: [22, 33, 44, 55]"
                + "  - numbers3: [3, 4, 5]"
                + "  + numbers4: [4, 5, 6]"
                + "  + obj1: {nestedKey=value, isNested=true}"
                + "  - setting1: Some value"
                + "  + setting1: Another value"
                + "  - setting2: 200"
                + "  + setting2: 300"
                + "  - setting3: true"
                + "  + setting3: none"
                + "\n}";
        var actual = Parser.parse("src/test/resources/nestedFile1.json",
                "src/test/resources/nestedFile2.json");
        assertEquals(expectedNestedFilesJSON, actual);
    }

    @Test
    public void testNestedFilesYAML() throws Exception {
        String expectedNestedFilesJSON = "{\n"
                + "  + chars1: [a, b, c]"
                + "  - chars2: [d, e, f]"
                + "  + chars2: false"
                + "  - checked: false"
                + "  + checked: true"
                //+ "  - default: null"
                //+ "  + default: [value1, value2]"
                //+ "  - id: 45"
                //+ "  + id: null"
                + "  - key1: value1"
                + "  + key2: value2"
                + "  numbers1: [1, 2, 3, 4]"
                + "  - numbers2: [2, 3, 4, 5]"
                + "  + numbers2: [22, 33, 44, 55]"
                + "  - numbers3: [3, 4, 5]"
                + "  + numbers4: [4, 5, 6]"
                + "  + obj1: {nestedKey=value, isNested=true}"
                + "  - setting1: Some value"
                + "  + setting1: Another value"
                + "  - setting2: 200"
                + "  + setting2: 300"
                + "  - setting3: true"
                + "  + setting3: none"
                + "\n}";
        var actual = Parser.parse("src/test/resources/nestedFile1.yaml",
                "src/test/resources/nestedFile2.yaml");
        assertEquals(expectedNestedFilesJSON, actual);
    }
    @Test
    public void testTwoIdenticalFilesJSON() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actual = Parser.parse("src/test/resources/testTwoIdenticalFile1.json",
                "src/test/resources/testTwoIdenticalFile2.json");
        assertEquals(expectedTwoIdenticalFiles, App.getData(actual));

    }

    @Test
    public void testTwoIdenticalFilesYAML() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actual = Parser.parse("src/test/resources/testTwoIdenticalFile1.yaml",
                "src/test/resources/testTwoIdenticalFile2.yaml");
        assertEquals(expectedTwoIdenticalFiles, App.getData(actual));

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
        var actual = Parser.parse("src/test/resources/fileWithSameKeys1.json",
                "src/test/resources/fileWithSameKeys2.json");
        assertEquals(expectedTwoDifferentFilesWithSameKeys, App.getData(actual));
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
        var actual = Parser.parse("src/test/resources/fileWithSameKeys1.yaml",
                "src/test/resources/fileWithSameKeys2.yaml");
        assertEquals(expectedTwoDifferentFilesWithSameKeys, App.getData(actual));
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


        var actual = Parser.parse("src/test/resources/differentFile1.json",
                "src/test/resources/differentFile2.json");
        assertEquals(expectedTwoDifferentFiles, App.getData(actual));
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


        var actual = Parser.parse("src/test/resources/differentFile1.yaml",
                "src/test/resources/differentFile2.yaml");
        assertEquals(expectedTwoDifferentFiles, App.getData(actual));
    }
}

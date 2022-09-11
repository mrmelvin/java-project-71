package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

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
                + "Property 'setting3' was updated. From true to 'none'\n";
        var actualPlainJSON = Parser.parse("src/test/resources/nestedFile1.json\n",
                "src/test/resources/nestedFile2.json");
        assertEquals(expectedPlainJSON, App.getDataPlain(actualPlainJSON));
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
                + "Property 'setting3' was updated. From true to 'none'\n";
        var actualPlainYAML = Parser.parse("src/test/resources/nestedFile1.json",
                "src/test/resources/nestedFile2.json");
        assertEquals(expectedPlainYAML, App.getDataPlain(actualPlainYAML));
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
        var actualNestedFilesJSON = Parser.parse("src/test/resources/nestedFile1.json",
                "src/test/resources/nestedFile2.json");
        assertEquals(expectedNestedFilesJSON, App.getDataDefault(actualNestedFilesJSON));
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
        assertEquals(expectedNestedFilesYAML, App.getDataDefault(actualNestedFilesYAML));
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
        assertEquals(expectedTwoIdenticalFiles, App.getDataDefault(actualTwoIdenticalFilesJSON));

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
        assertEquals(expectedTwoIdenticalFiles, App.getDataDefault(actualTwoIdenticalFilesYAML));

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
                Parser.parse("src/test/resources/fileWithSameKeys1.json",
                "src/test/resources/fileWithSameKeys2.json");
        assertEquals(expectedTwoDifferentFilesWithSameKeys,
                     App.getDataDefault(actualTwoDifferentFilesWithSameKeysJSON));
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
                Parser.parse("src/test/resources/fileWithSameKeys1.yaml",
                "src/test/resources/fileWithSameKeys2.yaml");
        assertEquals(expectedTwoDifferentFilesWithSameKeys,
                     App.getDataDefault(actualTwoDifferentFilesWithSameKeysYAML));
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
        assertEquals(expectedTwoDifferentFiles, App.getDataDefault(actualTwoDifferentFilesJSON));
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
        assertEquals(expectedTwoDifferentFiles, App.getDataDefault(actualTwoDifferentFilesYAML));
    }
}

package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {


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

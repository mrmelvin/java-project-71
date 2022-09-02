package hexlet.code;


import java.io.IOException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {


    @Test
    public void testTwoIdenticalFiles() throws Exception {
        String expectedTwoIdenticalFiles = "{\n"
                + "    capital: Berlin\n"
                + "    country: Germany\n"
                + "    currency: euro"
                + "\n}";

        var actual = App.parseTwoFiles("src/test/resources/testTwoIdenticalFile1.json",
                "src/test/resources/testTwoIdenticalFile2.json");
        assertEquals(expectedTwoIdenticalFiles, App.getData(actual));

    }


    @Test
    public void testTwoDifferentFilesWithSameKeys() throws IOException {
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
        var actual = App.parseTwoFiles("src/test/resources/fileWithSameKeys1.json",
                "src/test/resources/fileWithSameKeys2.json");
        assertEquals(expectedTwoDifferentFilesWithSameKeys, App.getData(actual));
    }

    @Test
    public void testTwoDifferentFiles() throws IOException {
        String expectedTwoDifferentFiles = "{\n"
                + "  - age: 30\n"
                + "  + director: Francis Ford Coppola\n"
                + "  + filmName: Godfather\n"
                + "  - firstName: John\n"
                + "  - lastName: Doe\n"
                + "  + year: 1972"
                + "\n}";


        var actual = App.parseTwoFiles("src/test/resources/differentFile1.json",
                "src/test/resources/differentFile2.json");
        assertEquals(expectedTwoDifferentFiles, App.getData(actual));
    }
}

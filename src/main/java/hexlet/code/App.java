package hexlet.code;


import java.util.concurrent.Callable;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;



@Command(name = "gendiff",
        mixinStandardHelpOptions = true,
        description = "Compares two configuration files and shows a difference."
                + "\n\tfilepath1\tpath to first file\n\tfilepath2\tpath to second file",
        version = "gendiff 0.1")

public class App implements Callable<Integer> {



    @Option(names = {"-f", "--format"}, defaultValue = "stylish",
            paramLabel = "format", description = "output format [default: stylish]")
    private String format;

    @Parameters(index = "0")
    private String basicFile;

    @Parameters(index = "1")
    private String changedFile;


    /**
     *
     * @return exitState code
     * @throws Exception
     */
    public Integer call() throws Exception {
        System.out.println(Differ.generate(basicFile, changedFile, format));
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

//final GLOBAL_DESCRIPTION = "Compares two configuration files and shows a difference.         filepath1         path to first file        filepath2         path to second file";
@Command(name = "gendiff", mixinStandardHelpOptions = true, description = "Compares two configuration files and shows a difference.\n\tfilepath1\tpath to first file\n\tfilepath2\tpath to second file")
public class App implements Callable<Integer> {

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    File file;

    public Integer call() throws Exception {
//        if (usageHelpRequested) {
//            System.out.println("Compares two configuration files and shows a difference.");
//        }
        return 0;
    }
    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

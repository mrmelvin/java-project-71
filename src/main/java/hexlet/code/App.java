package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

//    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.")
//    boolean usageHelpRequested;
//
//    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
//    boolean versionInfoRequested;

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

package ar.org.pachisoft.matelang.config;

import ar.org.pachisoft.matelang.error.ErrorHandler;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Handles command line parameters configuration and parsing.
 */
public class CommandLineParametersParser {
    private final Options options;
    private final String[] args;

    /**
     * Creates a new parameters.
     *
     * @param args Received command line arguments
     */
    public CommandLineParametersParser(String[] args) {
        this.options = new Options();
        this.args = args;
    }

    /**
     * Parses the specified arguments and returns an object with the configuration.
     *
     * @return Command line argument configuration.
     */
    public CommandLineParametersConfig parse() {
        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine commandLine = parser.parse(options, args);

            return CommandLineParametersConfig.builder()
                    .source(commandLine.getArgs().length > 0 ? commandLine.getArgs()[0] : "")
                    .build();
        } catch (ParseException ex) {
            ErrorHandler.showCLIUsageErrorAndExit(ex.getMessage(), options);
            return null;
        }
    }
}

package ar.org.pachisoft.matelang.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Handles command line parameters configuration and parsing.
 */
public class CommandLineParametersParser {
    private final Options options;
    private final CommandLine commandLine;

    /**
     * Creates a new parameters.
     *
     * @throws ParseException Error parsing the command line arguments.
     */
    public CommandLineParametersParser(String[] args) throws ParseException {
        this.options = new Options();
        CommandLineParser parser = new DefaultParser();
        this.commandLine = parser.parse(options, args);
    }

    /**
     * Parses the specified arguments and returns an object with the configuration.
     *
     * @return Command line argument configuration.
     * @throws ParseException Error when parsing the command line arguments.
     */
    public CommandLineParametersConfig parse() throws ParseException {
        return CommandLineParametersConfig.builder()
                .source(commandLine.getArgs().length > 0 ? commandLine.getArgs()[0] : "")
                .build();
    }

    /**
     * Shows the command line arguments help.
     */
    public void showHelp() {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("mate [options] [source]", options);
    }
}

package ar.org.pachisoft.matelang.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ParametersConfigParser {
    private static final String MODULE_PATH_OPTION = "m";
    private final Options options;

    private ParametersConfigParser() {
        this.options = new Options();

        options.addOption(MODULE_PATH_OPTION, "module", true,
                "Path to module file. By default ./module.config");

    }

    public ParametersConfig parse(String args[]) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        return ParametersConfig.builder()
                .moduleConfigPath(cmd.getOptionValue(MODULE_PATH_OPTION))
                .build();
    }
}

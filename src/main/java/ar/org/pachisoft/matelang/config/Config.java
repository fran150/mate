package ar.org.pachisoft.matelang.config;

import ar.org.pachisoft.matelang.utils.ConfigValueExtractor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;
import lombok.Getter;
import org.apache.commons.cli.ParseException;

/**
 * General configuration for the compiler, it includes internal values, parameters
 * and configuration of the module.
 */
public class Config {
    // FIXME: For now this is static, some of these might be customizable (via arguments?)
    private static final int ERROR_CONTEXT_LINES = 3;
    private static final String ERROR_OUTPUT_SPACES_PER_TAB = " ".repeat(4);
    private static final boolean SHOW_ERROR_CONTEXT = true;

    private static final String DEFAULT_SOURCE_DIR = "";
    private static final String MATE_MODULE_FILE_NAME = "module.config.json";
    private static final String DEFAULT_MODULE_VERSION = "1.0.0";
    private static final String[] DEFAULT_BUILD_PATHS;

    private static final Predicate<String> STRING_HAS_VALUE;
    private static final Predicate<String[]> STRING_ARRAY_HAS_VALUE;

    @Getter
    private final Path source;
    @Getter
    private final File moduleConfigFile;

    @Getter
    private final String name;
    @Getter
    private final String version;
    @Getter
    private final String author;
    @Getter
    private final Path[] buildPaths;

    static {
        DEFAULT_BUILD_PATHS = new String[2];
        DEFAULT_BUILD_PATHS[0] = "mate-modules";
        DEFAULT_BUILD_PATHS[1] = "src";

        STRING_HAS_VALUE = value -> value != null && !value.isBlank();
        STRING_ARRAY_HAS_VALUE = value -> value != null && value.length > 0;
    }

    /**
     * Creates a new configuration object.
     *
     * @param parametersConfigParser Command line parameters parser.
     * @param moduleConfigReader Module configuration file parser.
     * @throws ParseException Failed to parse the command line arguments.
     * @throws IOException Failed to read the module configuration file.
     */
    public Config(CommandLineParametersParser parametersConfigParser,
                  ModuleConfigReader moduleConfigReader)
            throws ParseException, IOException {

        CommandLineParametersConfig parameters = parametersConfigParser.parse();

        this.source = ConfigValueExtractor.getter(parameters::getSource)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> DEFAULT_SOURCE_DIR)
                .transform(Path::of)
                .validator(value -> {
                    if (!Files.isDirectory(value)) {
                        final String message = "The specified source is not a directory";
                        throw new IllegalArgumentException(message);
                    }
                });

        this.moduleConfigFile = this.source.resolve(MATE_MODULE_FILE_NAME).toFile();

        ModuleConfig moduleConfig = moduleConfigReader.read(this.moduleConfigFile);

        this.name = ConfigValueExtractor.getter(moduleConfig::getName)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> {
                    File directory = new File(this.source.toAbsolutePath().toString());
                    return directory.getName();
                })
                .validator();

        this.version = ConfigValueExtractor.getter(moduleConfig::getVersion)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> DEFAULT_MODULE_VERSION)
                .validator();

        this.author = moduleConfig.getAuthor();

        this.buildPaths = ConfigValueExtractor.getter(moduleConfig::getBuildPaths)
                .hasValue(STRING_ARRAY_HAS_VALUE)
                .defaultValue(() -> DEFAULT_BUILD_PATHS)
                .transform(paths -> Arrays.stream(paths)
                            .map(Path::of)
                            .toArray(Path[]::new))
                .transform(paths -> Arrays.stream(paths)
                            .map(path -> path.isAbsolute() ? path : source.resolve(path))
                            .toArray(Path[]::new))
                .validator();
    }

    /**
     * Returns the number of lines to show as context before and after an error.
     *
     * @return Number of lines of context for each error shown.
     */
    public int getErrorContextLines() {
        return ERROR_CONTEXT_LINES;
    }

    /**
     * If true, shows the surrounding lines of code around the line that caused the error.
     *
     * @return True if the error context must be shown.
     */
    public boolean showErrorContext() {
        return SHOW_ERROR_CONTEXT;
    }

    /**
     * When showing code, this value controls how many spaces each tab represents.
     *
     * @return Number of spaces per tab.
     */
    public String getErrorOutputSpacesPerTab() {
        return ERROR_OUTPUT_SPACES_PER_TAB;
    }

}

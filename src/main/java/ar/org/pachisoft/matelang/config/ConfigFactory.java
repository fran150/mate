package ar.org.pachisoft.matelang.config;

import ar.org.pachisoft.matelang.utils.ConfigValueExtractor;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Predicate;

@AllArgsConstructor
public class ConfigFactory {
    private static final String DEFAULT_SOURCE_DIR = "";
    private static final String MATE_MODULE_FILE_NAME = "module.config.json";
    private static final String DEFAULT_MODULE_VERSION = "1.0.0";
    private static final String[] DEFAULT_BUILD_PATHS;

    private static final Predicate<String> STRING_HAS_VALUE;
    private static final Predicate<String[]> STRING_ARRAY_HAS_VALUE;

    private final CommandLineParametersParser parametersConfigParser;
    private final ModuleConfigReader moduleConfigReader;

    static {
        DEFAULT_BUILD_PATHS = new String[3];
        DEFAULT_BUILD_PATHS[0] = "mate-modules";
        DEFAULT_BUILD_PATHS[1] = "src";
        DEFAULT_BUILD_PATHS[2] = "";

        STRING_HAS_VALUE = value -> value != null && !value.isBlank();
        STRING_ARRAY_HAS_VALUE = value -> value != null && value.length > 0;
    }

    /**
     * Creates a new configuration object.
     * @throws IOException Failed to read the module configuration file.
     */
    public Config loadConfiguration() {
        CommandLineParametersConfig parameters = parametersConfigParser.parse();

        Path source = ConfigValueExtractor.getter(parameters::getSource)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> DEFAULT_SOURCE_DIR)
                .transform(Path::of)
                .validator(value -> {
                    if (!Files.isDirectory(value)) {
                        final String message = "The specified source is not a directory";
                        throw new IllegalArgumentException(message);
                    }
                });

        File moduleConfigFile = source.resolve(MATE_MODULE_FILE_NAME).toFile();

        ModuleConfig moduleConfig = moduleConfigReader.read(moduleConfigFile);

        String name = ConfigValueExtractor.getter(moduleConfig::getName)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> {
                    File directory = new File(source.toAbsolutePath().toString());
                    return directory.getName();
                })
                .validator();

        String version = ConfigValueExtractor.getter(moduleConfig::getVersion)
                .hasValue(STRING_HAS_VALUE)
                .defaultValue(() -> DEFAULT_MODULE_VERSION)
                .validator();

        String author = moduleConfig.getAuthor();

        Path[] buildPaths = ConfigValueExtractor.getter(moduleConfig::getBuildPaths)
                .hasValue(STRING_ARRAY_HAS_VALUE)
                .defaultValue(() -> DEFAULT_BUILD_PATHS)
                .transform(paths -> Arrays.stream(paths)
                        .map(Path::of)
                        .toArray(Path[]::new))
                .transform(paths -> Arrays.stream(paths)
                        .map(path -> path.isAbsolute() ? path : source.resolve(path))
                        .filter(x -> x.toFile().exists() && x.toFile().isDirectory())
                        .toArray(Path[]::new))
                .validator();

        return Config.builder()
                .source(source)
                .moduleConfigFile(moduleConfigFile)
                .name(name)
                .version(version)
                .author(author)
                .buildPaths(buildPaths)
                .build();
    }
}

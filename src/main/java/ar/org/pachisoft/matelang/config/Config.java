package ar.org.pachisoft.matelang.config;

import java.io.File;

/**
 * General configuration for the compiler, it includes internal values, parameters
 * and configuration of the module
 */
public class Config {
    // FIXME: For now this is static, some of these might be customizable (via arguments?)
    public static final int ERROR_CONTEXT_LINES = 3;
    public static final String ERROR_OUTPUT_SPACES_PER_TAB = " ".repeat(4);
    public static final boolean SHOW_ERROR_CONTEXT = true;

    private final String name;
    private final String version;
    private final String author;
    private final String[] buildPaths;

    /**
     * Creates a new configuration object based on the config information for the
     * module.
     * @param moduleConfig Configuration of the module
     */
    public Config(ModuleConfig moduleConfig,
                  ParametersConfig parametersConfig) {
        String name = moduleConfig.getName();

        if (name != null && !name.isBlank()) {
            this.name = name.trim();
        } else {
            File directory = new File("./");
            this.name = directory.getName();
        }

        String version = moduleConfig.getVersion();
        if (version != null && !version.isBlank()) {
            this.version = version.trim();
        } else {
            this.version = "1.0.0";
        }

        this.author = moduleConfig.getAuthor();

        String[] buildPaths = moduleConfig.getBuildPaths();

        if (buildPaths == null || buildPaths.length > 0) {
            buildPaths = new String[2];

            buildPaths[0] = "./mate-modules";
            buildPaths[1] = "./src";
        }

        this.buildPaths = buildPaths;
    }

    /**
     * Returns the number of lines to show as context before and after an error
     * @return Number of lines of context for each error shown
     */
    public int getErrorContextLines() {
        return ERROR_CONTEXT_LINES;
    }

    /**
     * If true, shows the surrounding lines of code around the line that caused the error
     * @return True if the error context must be shown
     */
    public boolean isShowErrorContext() {
        return SHOW_ERROR_CONTEXT;
    }

    /**
     * When showing code, this value controls how many spaces each tab represents
     * @return Number of spaces per tab
     */
    public String getErrorOutputSpacesPerTab() {
        return ERROR_OUTPUT_SPACES_PER_TAB;
    }

    /**
     * Returns the current module name, if the name is not specified in the module
     * config file it returns the name of the current directory
     * @return Current module name
     */
    public String getModuleName() {
        return name;
    }

    /**
     * Returns the current module version, if its not specified in the module config
     * file it returns 1.0.0
     * @return Current module version
     */
    public String getModuleVersion() {
        return version;
    }

    /**
     * Returns the current module author.
     * @return Current module author.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Return the current build paths. This are the directories in where the compiler
     * will look for source files. If not specified in the module config the compiler
     * will use ./src and ./mate-modules
     * @return Current build paths.
     */
    public String[] getBuildPaths() {
        return buildPaths;
    }
}

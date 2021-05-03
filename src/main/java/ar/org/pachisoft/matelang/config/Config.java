package ar.org.pachisoft.matelang.config;

import lombok.Builder;
import lombok.Value;

import java.io.File;
import java.nio.file.Path;

/**
 * General configuration for the compiler, it includes internal values, parameters
 * and configuration of the module.
 */
@Value
@Builder
public class Config {
    // FIXME: For now this is static, some of these might be customizable (via arguments?)
    private static final int ERROR_CONTEXT_LINES = 3;
    private static final String ERROR_OUTPUT_SPACES_PER_TAB = " ".repeat(4);
    private static final boolean SHOW_ERROR_CONTEXT = true;

    Path source;
    File moduleConfigFile;

    String name;
    String version;
    String author;
    Path[] buildPaths;

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

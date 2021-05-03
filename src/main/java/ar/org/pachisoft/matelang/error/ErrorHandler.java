package ar.org.pachisoft.matelang.error;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.utils.ConsoleColors;
import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import java.io.File;

/**
 * Handles error presentation for both compiler and runtime errors.
 */
@AllArgsConstructor
public class ErrorHandler {
    private static final int EX_USAGE = 64;
    private static final int EX_CONFIG = 78;

    private final ConsoleUtils consoleUtils;
    private final Config config;

    /**
     * Shows the specified error on the standard error output.
     *
     * @param pointer Parsing pointer (contains information about the current state).
     * @param message Error message to show.
     */
    public void compilerError(ParsingPointer pointer, String message) {
        System.err.println(getErrorMessage(pointer, message));
    }

    private void appendMessageHeader(StringBuilder sb, ParsingPointer pointer) {
        if (pointer.getFile() != null) {
            sb.append(String.format("Parser error on line number %d of file %s:\n",
                    pointer.getLine() + 1, pointer.getFile().getName()));
        } else {
            sb.append("Error parsing statement:\n\n");
        }
    }

    private void appendErrorLine(StringBuilder sb, ParsingPointer pointer) {
        sb.append(pointer.getCurrentLine());
        sb.append("\n");
        sb.append(" ".repeat(pointer.getColumn()));
        consoleUtils.changeOutputColor(sb, ConsoleColors.YELLOW);
        sb.append("^--- here\n");
        consoleUtils.changeOutputColor(sb, ConsoleColors.RESET);
    }

    private void appendErrorMessage(StringBuilder sb, String message) {
        sb.append("Error: ");
        sb.append(message);
        sb.append("\n");
    }

    private void appendErrorLocation(StringBuilder sb, ParsingPointer pointer, String message) {
        consoleUtils.changeOutputColor(sb, ConsoleColors.GREEN);
        sb.append("Context:\n");
        consoleUtils.changeOutputColor(sb, ConsoleColors.RESET);

        int contextErrorLines = config.getErrorContextLines();

        sb.append(pointer.getPreContext(contextErrorLines));
        appendErrorLine(sb, pointer);
        sb.append(pointer.getPostContext(contextErrorLines));
    }

    String getErrorMessage(ParsingPointer pointer, String message) {
        StringBuilder sb = new StringBuilder();

        consoleUtils.changeOutputColor(sb, ConsoleColors.YELLOW);
        appendMessageHeader(sb, pointer);

        consoleUtils.changeOutputColor(sb, ConsoleColors.RED);
        appendErrorMessage(sb, message);

        if (config.showErrorContext()) {
            appendErrorLocation(sb, pointer, message);
        }

        consoleUtils.changeOutputColor(sb, ConsoleColors.RESET);

        return sb.toString();
    }

    /**
     * Shows the command line arguments help.
     */
    public static void showCLIUsageErrorAndExit(String error, Options cliOptions) {
        HelpFormatter helpFormatter = new HelpFormatter();
        System.out.println(error);
        helpFormatter.printHelp("mate [options] [source]", cliOptions);

        System.exit(EX_USAGE);
    }

    public static void showConfigErrorAndExit(String error) {
        System.out.print(ConsoleColors.RED);
        System.out.println(error);
        System.out.print(ConsoleColors.RESET);

        System.exit(EX_CONFIG);
    }

    public static void showModuleConfigFormatErrorAndExit(File moduleConfigFile, String error) {
        System.out.printf("Error while reading module config file: %s\n\n",
                moduleConfigFile);

        System.out.printf("Error description: %s\n", error);

        System.exit(EX_CONFIG);
    }
}
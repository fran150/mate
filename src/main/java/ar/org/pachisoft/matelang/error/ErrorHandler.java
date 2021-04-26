package ar.org.pachisoft.matelang.error;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.utils.ConsoleColors;
import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

/**
 * Handles error presentation for both compiler and runtime errors.
 */
@AllArgsConstructor
public class ErrorHandler {
    private ConsoleUtils consoleUtils;
    private Config config;

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
        if (pointer.getFileName() != null) {
            sb.append(String.format("Parser error on line number %d of file %s:\n",
                    pointer.getLine() + 1, pointer.getFileName()));
        } else {
            sb.append("Error parsing statement:\n\n");
        }
    }

    private void appendMessageContext(StringBuilder sb, String[] context) {
        sb.append(Arrays.stream(context)
                .filter(Objects::nonNull)
                .collect(Collectors.joining("\n")));
        sb.append("\n");
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

        appendMessageContext(sb, pointer.getPreContext());
        appendErrorLine(sb, pointer);
        appendMessageContext(sb, pointer.getPostContext());
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
}
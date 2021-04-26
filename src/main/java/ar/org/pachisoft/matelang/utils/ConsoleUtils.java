package ar.org.pachisoft.matelang.utils;

import ar.org.pachisoft.matelang.config.Environment;
import lombok.AllArgsConstructor;

/**
 * Utilities for preparing output for the console.
 */
@AllArgsConstructor
public class ConsoleUtils {
    private Environment environment;

    /**
     * Allows to change color being appended to a string builder.
     *
     * @param sb String builder in where to change colors.
     * @param color New color.
     */
    public void changeOutputColor(StringBuilder sb, String color) {
        if (environment.consoleSupportsColors()) {
            sb.append(changeOutputColor(color));
        }
    }

    /**
     * Returns the ANSI code that enables changing color.
     *
     * @param color New color of the text.
     *
     * @return ANSI code to change text color to the desired one.
     */
    public String changeOutputColor(String color) {
        if (environment.consoleSupportsColors()) {
            return color;
        } else {
            return "";
        }
    }
}

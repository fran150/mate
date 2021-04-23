package ar.org.pachisoft.matelang.utils;

import ar.org.pachisoft.matelang.config.Environment;
import lombok.AllArgsConstructor;

/**
 * Utilities for preparing output for the console
 */
@AllArgsConstructor
public class ConsoleUtils {
    private Environment environment;

    public void changeOutputColor(StringBuilder sb, String color) {
        if (environment.consoleSupportsColors()) {
            sb.append(changeOutputColor(color));
        }
    }

    public String changeOutputColor(String color) {
        if (environment.consoleSupportsColors()) {
            return color;
        } else {
            return "";
        }
    }
}

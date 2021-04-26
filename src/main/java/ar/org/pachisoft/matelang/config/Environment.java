package ar.org.pachisoft.matelang.config;

/**
 * Returns data about the environment in which the compiler is running.
 */
public class Environment {
    /**
     * Returns true if the console supports ANSI escape codes.
     *
     * @return True if the console allows to show colors.
     */
    public boolean consoleSupportsColors() {
        return (System.console() != null && System.getenv().get("TERM") != null);
    }
}

package ar.org.pachisoft.matelang.config;

import lombok.Builder;
import lombok.Value;

/**
 * Configuration obtained from the command line parameters.
 */
@Value
@Builder
public class CommandLineParametersConfig {
    String source;
}

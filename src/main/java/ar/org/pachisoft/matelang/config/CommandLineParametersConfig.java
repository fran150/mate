package ar.org.pachisoft.matelang.config;

import lombok.Builder;
import lombok.Data;

/**
 * Configuration obtained from the command line parameters.
 */
@Data
@Builder
public class CommandLineParametersConfig {
    private String source;
}

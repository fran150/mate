package ar.org.pachisoft.matelang.config;

import ar.org.pachisoft.matelang.error.ErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Reads and parses the module configuration file.
 */
public class ModuleConfigReader {
    /**
     * Read and parse the module configuration file.
     *
     * @param moduleConfigFile Module configuration file.
     * @return Parsed module configuration file.
     */
    public ModuleConfig read(File moduleConfigFile) {
        ObjectMapper mapper = new ObjectMapper();

        if (moduleConfigFile.exists()) {
            try {
                return mapper.readValue(moduleConfigFile, ModuleConfig.class);
            } catch (IOException ex) {
                ErrorHandler.showModuleConfigFormatErrorAndExit(moduleConfigFile, ex.getMessage());
                return null;
            }
        } else {
            return new ModuleConfig();
        }
    }
}

package ar.org.pachisoft.matelang.config;

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
     * @throws IOException Thrown when it was not possible to read
     *                     the specified module configuration path.
     */
    public ModuleConfig read(File moduleConfigFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        if (moduleConfigFile.exists()) {
            return mapper.readValue(moduleConfigFile, ModuleConfig.class);
        } else {
            return ModuleConfig.builder().build();
        }
    }
}

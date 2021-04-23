package ar.org.pachisoft.matelang.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ModuleConfigReader {
    private final String moduleConfigPath;

    public ModuleConfigReader(ParametersConfig parametersConfig) {
        this.moduleConfigPath = parametersConfig.getModuleConfigPath();
    }

    public ModuleConfig read() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File moduleConfigFile = new File(moduleConfigPath);
        return mapper.readValue(moduleConfigFile, ModuleConfig.class);
    }
}

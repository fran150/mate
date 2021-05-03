package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.config.CommandLineParametersParser;
import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.config.ConfigFactory;
import ar.org.pachisoft.matelang.config.ModuleConfigReader;
import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * Configuration module for dagger. It sets up the injection for configuration objects.
 */
@Module(includes = {
    ErrorHandlerModule.class
})
@NoArgsConstructor
@AllArgsConstructor
public class ConfigModule {
    private String[] args;

    @Provides
    public ModuleConfigReader provideModuleConfigReader() {
        return new ModuleConfigReader();
    }

    @Provides
    public CommandLineParametersParser provideCommandLineParametersParser() {
        return new CommandLineParametersParser(args);
    }

    @Provides
    public ConfigFactory provideConfigFactory(CommandLineParametersParser commandLineParametersParser,
                                              ModuleConfigReader moduleConfigReader) {
        return new ConfigFactory(commandLineParametersParser, moduleConfigReader);
    }

    @Provides
    public Config provideConfig(ConfigFactory configFactory) {
        return configFactory.loadConfiguration();
    }
}

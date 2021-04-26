package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.config.CommandLineParametersParser;
import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.config.ModuleConfigReader;
import dagger.Module;
import dagger.Provides;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

/**
 * Configuration module for dagger. It sets up the injection for configuration objects.
 */
@Module
@NoArgsConstructor
@AllArgsConstructor
public class ConfigModule {
    private String[] args;

    @Provides
    public ModuleConfigReader provideModuleConfigReader() {
        return new ModuleConfigReader();
    }

    @Provides
    @SneakyThrows // FIXME: Remove this and properly handle the error
    public CommandLineParametersParser provideCommandLineParametersParser() {
        return new CommandLineParametersParser(args);
    }

    @Provides
    @SneakyThrows // FIXME: Remove this and properly handle the error
    public Config provideConfig(CommandLineParametersParser parser,
                                ModuleConfigReader moduleConfigReader) {
        return new Config(parser, moduleConfigReader);
    }
}

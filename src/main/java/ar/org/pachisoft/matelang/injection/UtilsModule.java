package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import ar.org.pachisoft.matelang.utils.Environment;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {
    @Provides
    public Environment provideEnvironment() {
        return new Environment();
    }

    @Provides
    public ConsoleUtils provideConsoleUtils(Environment environment) {
        return new ConsoleUtils(environment);
    }
}

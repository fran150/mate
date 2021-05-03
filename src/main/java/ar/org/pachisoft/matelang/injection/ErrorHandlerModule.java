package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.error.ErrorHandler;
import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import dagger.Module;
import dagger.Provides;

@Module(includes = {
    UtilsModule.class,
    ConfigModule.class
})
public class ErrorHandlerModule {
    @Provides
    public ErrorHandler provideErrorHandler(ConsoleUtils consoleUtils,
                                            Config config) {
        return new ErrorHandler(consoleUtils, config);
    }
}

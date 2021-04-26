package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.main.MateCompiler;
import dagger.Module;
import dagger.Provides;

/**
 * Main module that configures injection for main classes.
 */
@Module
public class MainModule {
    @Provides
    public MateCompiler provideMateCompiler(Config config) {
        return new MateCompiler(config);
    }
}

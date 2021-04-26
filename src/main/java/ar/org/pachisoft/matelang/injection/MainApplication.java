package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.main.MateCompiler;
import dagger.Component;

/**
 * Main application.
 */
@Component(modules = {
    ConfigModule.class,
    MainModule.class
})
public interface MainApplication {
    MateCompiler compiler();
}

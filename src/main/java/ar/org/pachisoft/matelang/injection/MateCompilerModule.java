package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.main.MateCompiler;
import ar.org.pachisoft.matelang.scanner.BuildPathIterator;
import ar.org.pachisoft.matelang.scanner.Scanner;
import dagger.Module;
import dagger.Provides;

/**
 * Main module that configures injection for main classes.
 */
@Module
public class MateCompilerModule {
    @Provides
    public MateCompiler provideMateCompiler(BuildPathIterator buildPathIterator,
                                            Scanner scanner) {
        return new MateCompiler(buildPathIterator, scanner);
    }
}

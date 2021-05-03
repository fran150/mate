package ar.org.pachisoft.matelang.injection;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.scanner.BuildPathIterator;
import ar.org.pachisoft.matelang.scanner.Scanner;
import dagger.Module;
import dagger.Provides;

@Module(includes = ConfigModule.class)
public class ScannerModule {
    @Provides
    public BuildPathIterator provideBuildPathIterator(Config config) {
        return new BuildPathIterator(config);
    }

    @Provides
    public Scanner provideScanner() {
        return new Scanner();
    }
}

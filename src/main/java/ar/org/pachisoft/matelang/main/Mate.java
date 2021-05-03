package ar.org.pachisoft.matelang.main;

import ar.org.pachisoft.matelang.injection.ConfigModule;
import ar.org.pachisoft.matelang.injection.DaggerMainApplication;
import ar.org.pachisoft.matelang.injection.MainApplication;
import ar.org.pachisoft.matelang.injection.MateCompilerModule;
import ar.org.pachisoft.matelang.injection.ScannerModule;

/**
 * Main application for the mate compiler.
 */
public class Mate {
    /**
     * Invoked when the program starts.
     *
     * @param args parameters received from the console.
     */
    public static void main(String[] args) {
        MainApplication main = DaggerMainApplication.builder()
                .mateCompilerModule(new MateCompilerModule())
                .configModule(new ConfigModule(args))
                .scannerModule(new ScannerModule())
                .build();

        MateCompiler compiler = main.compiler();
        compiler.firstPass();
    }
}
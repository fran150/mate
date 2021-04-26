package ar.org.pachisoft.matelang.main;

import ar.org.pachisoft.matelang.injection.ConfigModule;
import ar.org.pachisoft.matelang.injection.DaggerMainApplication;
import ar.org.pachisoft.matelang.injection.MainApplication;
import ar.org.pachisoft.matelang.injection.MainModule;
import java.io.IOException;

/**
 * Main application for the mate compiler.
 */
public class Mate {
    /* https://www.freebsd.org/cgi/man.cgi?query=sysexits&apropos=0&sektion=0&manpath=FreeBSD+4.3-RELEASE&format=html */
    private static final int EX_USAGE = 64;

    static boolean hadError = false;

    private void exitAndShowUsage() {
        System.out.println("Usage: mate [script]");
        System.exit(EX_USAGE);
    }

    /**
     * Invoked when the program starts.
     *
     * @param args parameters received from the console.
     */
    public static void main(String[] args) {
        MainApplication main = DaggerMainApplication.builder()
                .mainModule(new MainModule())
                .configModule(new ConfigModule(args))
                .build();

        MateCompiler compiler = main.compiler();
        compiler.firstPass();
    }
}
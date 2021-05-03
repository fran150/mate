package ar.org.pachisoft.matelang.main;

import ar.org.pachisoft.matelang.scanner.BuildPathIterator;
import ar.org.pachisoft.matelang.scanner.Scanner;
import ar.org.pachisoft.matelang.scanner.Token;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for the mate compiler.
 */
@AllArgsConstructor
public class MateCompiler {
    private final BuildPathIterator buildPathIterator;
    private final Scanner scanner;

    /**
     * Apply a first compilation pass.
     */
    public void firstPass() {
        List<Token> tokens = new ArrayList<>();

        try {
            buildPathIterator.iterate((file, source) -> {
                scanner.scanTokens(file, source, tokens);
            });

            System.out.println(tokens);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

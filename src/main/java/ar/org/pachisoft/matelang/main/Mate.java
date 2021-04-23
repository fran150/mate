package ar.org.pachisoft.matelang.main;

import ar.org.pachisoft.matelang.scanner.Scanner;
import ar.org.pachisoft.matelang.scanner.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Mate {
    /* https://www.freebsd.org/cgi/man.cgi?query=sysexits&apropos=0&sektion=0&manpath=FreeBSD+4.3-RELEASE&format=html */
    private static final int EX_USAGE = 64;

    static boolean hadError = false;

    private void exitAndShowUsage() {
        System.out.println("Usage: mate [script]");
        System.exit(EX_USAGE);
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            runClassPath();
        } else {
            System.out.println("Welcome to mate CLI!!");
            runPrompt();
        }
    }
    
    private static void runClassPath(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));

        if (hadError) System.exit(65);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        // For now, just print the tokens.
        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}
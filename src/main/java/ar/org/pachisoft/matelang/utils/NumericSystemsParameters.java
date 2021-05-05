package ar.org.pachisoft.matelang.utils;

import java.util.HashMap;
import java.util.Map;

public class NumericSystemsParameters {
    public static final String BINARY_START = "0b";
    public static final int BINARY_NUMERIC_BASE = 2;
    public static final Map<Character, Integer> BINARY_SYMBOLS;

    public static final String OCTAL_START = "0o";
    public static final int OCTAL_NUMERIC_BASE = 8;
    public static final Map<Character, Integer> OCTAL_SYMBOLS;

    public static final String HEX_START = "0x";
    public static final int HEX_NUMERIC_BASE = 16;
    public static final Map<Character, Integer> HEX_SYMBOLS;
    static {
        BINARY_SYMBOLS = new HashMap<>();
        BINARY_SYMBOLS.put('0', 0);
        BINARY_SYMBOLS.put('1', 1);

        OCTAL_SYMBOLS = new HashMap<>();
        OCTAL_SYMBOLS.put('0', 0);
        OCTAL_SYMBOLS.put('1', 1);
        OCTAL_SYMBOLS.put('2', 2);
        OCTAL_SYMBOLS.put('3', 3);
        OCTAL_SYMBOLS.put('4', 4);
        OCTAL_SYMBOLS.put('5', 5);
        OCTAL_SYMBOLS.put('6', 6);
        OCTAL_SYMBOLS.put('7', 7);

        HEX_SYMBOLS = new HashMap<>();
        HEX_SYMBOLS.put('0', 0);
        HEX_SYMBOLS.put('1', 1);
        HEX_SYMBOLS.put('2', 2);
        HEX_SYMBOLS.put('3', 3);
        HEX_SYMBOLS.put('4', 4);
        HEX_SYMBOLS.put('5', 5);
        HEX_SYMBOLS.put('6', 6);
        HEX_SYMBOLS.put('7', 7);
        HEX_SYMBOLS.put('8', 8);
        HEX_SYMBOLS.put('9', 9);
        HEX_SYMBOLS.put('A', 10);
        HEX_SYMBOLS.put('B', 11);
        HEX_SYMBOLS.put('C', 12);
        HEX_SYMBOLS.put('D', 13);
        HEX_SYMBOLS.put('E', 14);
        HEX_SYMBOLS.put('F', 15);
    }
}

package ar.org.pachisoft.matelang.utils;

import java.util.HashMap;
import java.util.Map;

public enum ReservedWord {
    TRUE, FALSE, BOOL,
    UINT, UINT8, UINT16, UINT32, UINT64,
    INT, INT8, INT16, INT32, INT64,
    DECIMAL32, DECIMAL64,
    CHAR, STRING,
    VOID,
    LEN,
    PACKAGE,
    STRUCT, SERVICE, INTERFACE,
    PUBLIC, PRIVATE,
    READONLY, WRITABLE, WRITEONLY, READABLE,
    IMPORT,
    AS, IS, INTO,
    USE,
    RETURN,
    SELECTABLE, BY, DEFAULT,
    WHILE, FOR,
    IF, ELSE, SWITCH, CASE,
    BREAK, CONTINUE,
    TRY, CATCH, THROW;

    private static final Map<String, ReservedWord> MAPPING;

    static {
        MAPPING = new HashMap<>();

        MAPPING.put("true", TRUE);
        MAPPING.put("false", FALSE);

        MAPPING.put("bool", BOOL);

        MAPPING.put("uint", UINT);
        MAPPING.put("uint8", UINT8);
        MAPPING.put("uint16", UINT16);
        MAPPING.put("uint32", UINT32);
        MAPPING.put("uint64", UINT64);

        MAPPING.put("int", INT);
        MAPPING.put("int8", INT8);
        MAPPING.put("int16", INT16);
        MAPPING.put("int32", INT32);
        MAPPING.put("int64", INT64);

        MAPPING.put("decimal32", DECIMAL32);
        MAPPING.put("decimal64", DECIMAL64);

        MAPPING.put("char", CHAR);
        MAPPING.put("string", STRING);

        MAPPING.put("void", VOID);

        MAPPING.put("len", LEN);

        MAPPING.put("package", PACKAGE);
        MAPPING.put("struct", STRUCT);
        MAPPING.put("service", SERVICE);
        MAPPING.put("interface", INTERFACE);

        MAPPING.put("public", PUBLIC);
        MAPPING.put("private", PRIVATE);
        MAPPING.put("readonly", READONLY);
        MAPPING.put("writable", WRITABLE);
        MAPPING.put("writeonly", WRITEONLY);
        MAPPING.put("readable", READABLE);

        MAPPING.put("import", IMPORT);

        MAPPING.put("as", AS);
        MAPPING.put("is", IS);
        MAPPING.put("into", INTO);
        MAPPING.put("use", USE);
        MAPPING.put("return", RETURN);
        MAPPING.put("selectable", SELECTABLE);
        MAPPING.put("by", BY);
        MAPPING.put("default", DEFAULT);

        MAPPING.put("while", WHILE);
        MAPPING.put("for", FOR);
        MAPPING.put("if", IF);
        MAPPING.put("else", ELSE);
        MAPPING.put("switch", SWITCH);
        MAPPING.put("case", CASE);
        MAPPING.put("break", BREAK);
        MAPPING.put("continue", CONTINUE);

        MAPPING.put("try", TRY);
        MAPPING.put("catch", CATCH);
        MAPPING.put("throw", THROW);
    }

    public static boolean isReservedWord(String lexeme) {
        return MAPPING.containsKey(lexeme);
    }

    public static ReservedWord getReservedWordFor(String lexeme) {
        return MAPPING.get(lexeme);
    }
}

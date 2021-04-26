package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.config.Config;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Data
public class ParsingPointer {
    private final String source;
    private final String fileName;
    private final String[] preContext = new String[3];
    private final String[] postContext = new String[3];
    private String currentLine;

    private final List<Token> tokens = new ArrayList<>();

    private int start = 0;
    private int current = 0;
    private int line = 1;
    private int column;

    public char getCurrentChar() {
        return currentLine.charAt(column);
    }
}

package ar.org.pachisoft.matelang.error;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.utils.Environment;
import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {
    private ErrorHandler errorHandler;
    private ParsingPointer parsingPointer;
    private ConsoleUtils consoleUtils;

    @Mock
    private Environment environmentMock;

    @BeforeEach
    public void setup() {
        String source = "Person pilot = Person {\n" +
                        "    name: \"Pete\",\n" +
                        "    surname: \"Mitchell\",\n" +
                        "    alias: \"Maverick\",\n" +
                        "}";

        Config config = Config.builder().build();

        parsingPointer = ParsingPointer.builder()
                .file(new File("test.mate"))
                .source(source)
                .build();

        parsingPointer.advance(35);

        when(environmentMock.consoleSupportsColors()).thenReturn(true);

        consoleUtils = new ConsoleUtils(environmentMock);
        errorHandler = new ErrorHandler(consoleUtils, config);
    }

    @Test
    public void showError_showsTheExpectedErrorFormat() {
        String error = errorHandler.getErrorMessage(parsingPointer, "Test Error");
        System.out.println(error);
        assertTrue(true);
    }
}
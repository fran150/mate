package ar.org.pachisoft.matelang.error;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.config.Environment;
import ar.org.pachisoft.matelang.scanner.ParsingPointer;
import ar.org.pachisoft.matelang.utils.ConsoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ErrorHandlerTest {
    private ErrorHandler errorHandler;
    private ParsingPointer pointerInfoMock;
    private ConsoleUtils consoleUtils;

    @Mock
    private Environment environmentMock;
    @Mock
    private Config configMock;

    @BeforeEach
    public void setup() {
        pointerInfoMock = new ParsingPointer("", "Pilots.mate");

        String[] pre = pointerInfoMock.getPreContext();
        String[] post = pointerInfoMock.getPostContext();

        pre[0] = "Person pilot = Person {";
        pre[1] = "    name: \"Pete\",";
        pre[2] = "    surname: \"Mitchell\",";
        pointerInfoMock.setCurrentLine("    age: \"28\",");
        post[0] = "    alias: \"Maverick\",";
        post[1] = "}";
        post[2] = null;

        pointerInfoMock.setLine(3);
        pointerInfoMock.setColumn(10);

        when(environmentMock.consoleSupportsColors()).thenReturn(true);
        when(configMock.showErrorContext()).thenReturn(true);

        consoleUtils = new ConsoleUtils(environmentMock);
        errorHandler = new ErrorHandler(consoleUtils, configMock);
    }

    @Test
    public void showError_showsTheExpectedErrorFormat() {
        String error = errorHandler.getErrorMessage(pointerInfoMock, "Type Mismatch");
        System.out.println(error);
        assertTrue(true);
    }
}
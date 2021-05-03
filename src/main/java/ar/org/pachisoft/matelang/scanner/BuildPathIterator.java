package ar.org.pachisoft.matelang.scanner;

import ar.org.pachisoft.matelang.config.Config;
import ar.org.pachisoft.matelang.error.ErrorHandler;
import ar.org.pachisoft.matelang.utils.Constants;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Collection;
import java.util.function.BiConsumer;

/**
 * Iterates over each file in the build path.
 */
@AllArgsConstructor
public class BuildPathIterator {
    private final Config config;

    public void iterate(BiConsumer<File, String> processor) throws IOException {
        Path[] paths = config.getBuildPaths();

        if (paths.length == 0) {
            ErrorHandler.showConfigErrorAndExit("None of the specified build paths can be found");
        }

        for (Path path : paths) {
            Collection<File> files = FileUtils.listFiles(
                    path.toFile(),
                    new String[] { Constants.MATE_FILE_EXTENSION },
                    true
            );

            for (File file : files) {
                byte[] bytes = FileUtils.readFileToByteArray(file);
                String source = new String(bytes, Charset.defaultCharset());

                processor.accept(file, source);
            }
        }
    }
}

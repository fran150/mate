package ar.org.pachisoft.matelang.main;

import ar.org.pachisoft.matelang.config.Config;
import java.nio.file.Path;
import lombok.AllArgsConstructor;

/**
 * Main class for the mate compiler.
 */
@AllArgsConstructor
public class MateCompiler {
    private final Config config;

    /**
     * Apply a first compilation pass.
     */
    public void firstPass() {
        System.out.println(config.getModuleConfigFile().getAbsolutePath());

        for (Path buildPath : config.getBuildPaths()) {
            System.out.println(buildPath.toAbsolutePath());
        }
        //            File buildPathDir = new File(buildPath);
        //
        //            Collection<File> files = FileUtils.listFiles(
        //                    buildPathDir,
        //                    new String[] { "mate" },
        //                    true
        //            );
        //
        //            for (File source : files) {
        //                byte[] bytes = FileUtils.readFileToByteArray(source);
        //                run(new String(bytes, Charset.defaultCharset()));
        //            }
        //        }
        //
        //        if (hadError) System.exit(65);
    }
}

package ar.org.pachisoft.matelang.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class ModuleConfig {
    String name;
    String version;
    String author;
    String[] buildPaths;
}

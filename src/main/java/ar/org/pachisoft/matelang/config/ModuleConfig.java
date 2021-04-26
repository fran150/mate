package ar.org.pachisoft.matelang.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class ModuleConfig {
    private String name;
    private String version;
    private String author;
    private String[] buildPaths;
}

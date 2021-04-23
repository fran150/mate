package ar.org.pachisoft.matelang.config;

import lombok.Data;

@Data
class ModuleConfig {
    private String name;
    private String version;
    private String author;
    private String[] buildPaths;
}

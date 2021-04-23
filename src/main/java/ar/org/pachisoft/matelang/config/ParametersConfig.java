package ar.org.pachisoft.matelang.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
public class ParametersConfig {
    @Getter
    private String moduleConfigPath;
}

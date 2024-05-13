package com.home.SpringBootAutomation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@SuperBuilder

public class Base {
    @Version
    @JsonIgnore
    private Long versionId;

    @JsonIgnore
    private boolean deleted;

    @JsonIgnore
    private boolean editing;
}

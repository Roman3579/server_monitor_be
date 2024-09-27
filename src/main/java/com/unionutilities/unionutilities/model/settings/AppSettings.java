package com.unionutilities.unionutilities.model.settings;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
public class AppSettings {
    private Set<String> targets = new HashSet<>();
}

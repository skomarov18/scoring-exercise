package com.tools.scoring.service;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public interface TextFileReader {
    Optional<List<String>> getNames(Path source);
}

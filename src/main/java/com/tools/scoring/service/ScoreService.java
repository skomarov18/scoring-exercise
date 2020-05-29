package com.tools.scoring.service;

import java.nio.file.Path;

public interface ScoreService {
    long getTotalScore(Path source);
}

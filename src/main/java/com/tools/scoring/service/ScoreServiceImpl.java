package com.tools.scoring.service;

import com.tools.scoring.algorithm.IScoring;
import com.tools.scoring.algorithm.ScoringAlgorithmFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@Slf4j
public class ScoreServiceImpl implements ScoreService {

    private final IScoring scoreJob;
    private final TextFileReader fileService;

    public ScoreServiceImpl(@Autowired TextFileReader fileService, @Autowired ScoringAlgorithmFactory factory){
       this.fileService=fileService;
       this.scoreJob=factory.selectAlgorithm();
    }

    @Override
    public long getTotalScore(Path source) {
        if (fileService.getNames(source).isPresent()){
            return scoreJob.scoreTotalFile(fileService.getNames(source).get());
        } else {
            return 0;
        }

    }
}

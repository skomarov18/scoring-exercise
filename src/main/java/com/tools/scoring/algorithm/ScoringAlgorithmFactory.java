package com.tools.scoring.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ScoringAlgorithmFactory {

    @Value("${algorithm.name:}")
    private String name;


    public IScoring selectAlgorithm(){
        IScoring selection;
        log.debug("Searching for algorithm implementation for name {} ", name);
        //extend once there is more than one available
        switch (name) {
            default:
                selection =  new SimpleNameScore();
                break;
        }
        return selection;


    }
}

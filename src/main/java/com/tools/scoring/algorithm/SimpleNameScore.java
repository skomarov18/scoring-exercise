package com.tools.scoring.algorithm;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Slf4j
public class SimpleNameScore implements IScoring {

    @Override
    public long scoreTotalFile(final List<String> sortedNames) {
        if (sortedNames == null || sortedNames.isEmpty()) {
            log.warn("Nothing to score !");
            return 0;
        }
        Map<Integer,String> indexedNames = IntStream.range(0,sortedNames.size()).boxed().collect(Collectors.toMap(i->i+1, sortedNames::get));
        return indexedNames.entrySet().parallelStream().mapToInt(e->e.getKey() * scoreName(e.getValue())).sum();
    }

    private int scoreName(final String name) {
        if (name == null || name.isEmpty()) {
            log.warn("Nothing to score !");
            return 0;
        }
        int result = name.chars().filter(ch -> ch >= 'A' && ch <= 'Z').reduce(0, (subtotal, ch)
                ->subtotal + 1 + (ch - 'A')); //'A' -'A' =0, 'B'-'A'=1, etc. adding 1 to start the score from 1
        log.debug("name {} sum {} ", name, result);
        return result;
    }

}

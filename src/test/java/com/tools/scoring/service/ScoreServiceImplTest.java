package com.tools.scoring.service;

import com.tools.scoring.algorithm.ScoringAlgorithmFactory;
import com.tools.scoring.algorithm.SimpleNameScore;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceImplTest {

    private ScoreServiceImpl underTest;

    @Mock
    private SimpleNameScore scoreJob;

    @Mock
    private TextFileReader fileService;

    @Mock
    ScoringAlgorithmFactory factory;

    @Mock
    Path source;

    @Before
    public void setUp() {
        when(factory.selectAlgorithm()).thenReturn(scoreJob);
        underTest = new ScoreServiceImpl(fileService, factory);
    }

    @Test
    public void testNoNamesFound(){
        when(fileService.getNames(any())).thenReturn(Optional.empty());
        long total = underTest.getTotalScore(source);
        assertEquals(0,total);
    }

    @Test
    public void testNamesFound(){
        when(fileService.getNames(any())).thenReturn(Optional.of(Arrays.asList("JOHN","MARK")));
        when(scoreJob.scoreTotalFile(anyList())).thenReturn(2456L);
        long total = underTest.getTotalScore(source);
        assertEquals(2456,total);
    }
}

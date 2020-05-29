package com.tools.scoring.algorithm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class SimpleNameScoreTest {
    private SimpleNameScore underTest;

    @Before
    public void setUp() {
        underTest = new SimpleNameScore();
    }

    @Test
    public void validateTotalScore() {
        List<String> sortedNames = Arrays.asList("ABBY","JOSH","BETTY");
        long total = underTest.scoreTotalFile(sortedNames);
        assertEquals(350,total);
    }

    @Test
    public void validateNullList() {
        long total = underTest.scoreTotalFile(null);
        assertEquals(0,total);
    }

    @Test
    public void validateEmptyList() {
        long total = underTest.scoreTotalFile(new ArrayList<>());
        assertEquals(0,total);
    }

    @Test
    public void validateTotalScoreWithEmptyName() {
        List<String> sortedNames = Arrays.asList("ABBY","","BETTY");
        long total = underTest.scoreTotalFile(sortedNames);
        assertEquals(246,total);
    }

}

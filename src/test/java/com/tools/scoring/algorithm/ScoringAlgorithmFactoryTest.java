package com.tools.scoring.algorithm;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ScoringAlgorithmFactoryTest {
    private ScoringAlgorithmFactory underTest;

    @Before
    public void setUp() {
        underTest = new ScoringAlgorithmFactory();
        ReflectionTestUtils.setField(underTest, "name", "simple");
    }

    @Test
    public void checkDefaultAlgorithmSelected(){
        IScoring defaultSelection = underTest.selectAlgorithm();
        assertEquals(SimpleNameScore.class, defaultSelection.getClass());
    }

}

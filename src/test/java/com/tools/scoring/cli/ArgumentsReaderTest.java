package com.tools.scoring.cli;

import com.tools.scoring.service.ScoreService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.ApplicationArguments;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentsReaderTest {
    private ArgumentsReader underTest;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Mock
    ScoreService scoreService;

    @Mock
    ApplicationArguments args;


    @Before
    public void setUp() {
        underTest = new ArgumentsReader(scoreService);
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void validateFileIsMissing(){
       when(args.getOptionValues("file.name")).thenReturn(null);
       underTest.run(args);
       assertTrue(outContent.toString().contains("Please provide a single valid file name"));
    }

    @Test
    public void validateFileIsFound(){
        when(args.getOptionValues("file.name")).thenReturn(Arrays.asList("test.txt"));
        when(scoreService.getTotalScore(any())).thenReturn( 1000L);
        underTest.run(args);
        assertTrue(outContent.toString().contains("The total score for file test.txt is 1000"));
    }

    @Test
    public void validateFileIsFoundButTotalIsZero(){
        when(args.getOptionValues("file.name")).thenReturn(Arrays.asList("test.txt"));
        when(scoreService.getTotalScore(any())).thenReturn( 0L);
        underTest.run(args);
        assertTrue(outContent.toString().contains("The total score for file test.txt is 0"));
    }

}

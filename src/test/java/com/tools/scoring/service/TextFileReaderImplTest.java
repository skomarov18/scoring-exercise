package com.tools.scoring.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class TextFileReaderImplTest {
 private TextFileReaderImpl underTest;
 private ClassLoader classLoader;

    @Before
    public void setUp() {
        underTest = new TextFileReaderImpl();
        classLoader = getClass().getClassLoader();
    }

    @Test
    public void validateFileIsEmpty()  {
        File file = new File(classLoader.getResource("empty_test.txt").getFile());
        Path source =  file.toPath();
        Optional<List<String>> result =  underTest.getNames(source);
        assertFalse(result.isPresent());
    }
    @Test
    public void validateFileHasNames()  {
        File file = new File(classLoader.getResource("test.txt").getFile());
        Path source =  file.toPath();
        Optional<List<String>> result =  underTest.getNames(source);
        assertTrue(result.isPresent());
        List<String> names = result.get();
        assertEquals(9,names.size());
        assertEquals("BARBARA",names.get(0));
        assertEquals("VINCENZO",names.get(8));
        assertEquals("HAI",names.get(1));
    }
}

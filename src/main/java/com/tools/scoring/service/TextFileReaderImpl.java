package com.tools.scoring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Slf4j
public class TextFileReaderImpl implements TextFileReader {

    private final static String DELIMITER = ",";
    private final static String DOUBLE_QUOTE = "\"";
    private final static String EMPTY = "";

    @Override
    public Optional<List<String>> getNames(Path source) {
        try (Scanner scanner = new Scanner(source)){
            if (!scanner.hasNextLine()) {
                log.warn("File {} is empty score will be zero!",source.getFileName());
                return Optional.empty();
            } else {
                String line = scanner.nextLine();
                log.debug(line);
                return Optional.of(Stream.of(line.split(DELIMITER)).map(s->s.replaceAll(DOUBLE_QUOTE,EMPTY)).
                        map(String::toUpperCase).sorted().collect(Collectors.toList()));

            }
        } catch (IOException | RuntimeException e) {
           log.error("Can't read the file {} {} ",source.getFileName(), e);
           return Optional.empty();
        }
    }
}

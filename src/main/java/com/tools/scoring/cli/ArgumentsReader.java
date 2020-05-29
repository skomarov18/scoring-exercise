package com.tools.scoring.cli;

import com.tools.scoring.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;


@Profile("cli")
@Service
@Slf4j
public class ArgumentsReader implements ApplicationRunner {

    private final ScoreService service;

    public ArgumentsReader(@Autowired ScoreService service) {
        this.service = service;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (args.getOptionValues("file.name") == null || args.getOptionValues("file.name").size() != 1){
            System.out.println("Please provide a single valid file name. For example --file.name=sample.txt");
        } else {
            String fileName = args.getOptionValues("file.name").get(0);
            log.debug("Received file {} for scoring",fileName);
            Path source = Paths.get(fileName);
            long total = service.getTotalScore(source);
            if (total == 0){
                log.warn("total score for file {} is zero. Please check the file exist and it is not empty", fileName);
            } else {
                log.debug("total score for file {} is {}", fileName, total);
            }
            System.out.println(String.format("The total score for file %s is %d ",fileName, total ));
        }

    }
}

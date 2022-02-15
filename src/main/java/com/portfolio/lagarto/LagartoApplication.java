package com.portfolio.lagarto;

import com.portfolio.lagarto.auction.FileUploadController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



@SpringBootApplication

public class LagartoApplication {

    public static void main(String[] args) {

        SpringApplication.run(LagartoApplication.class, args);
    }


}

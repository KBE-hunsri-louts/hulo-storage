package com.huloteam.kbe.csv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.io.File;

@Configuration
@PropertySource({"classpath:csv.properties"})
public class CSVConfig {
    @Autowired
    public Environment environment;

    @Bean
    public File csvPath() {
        return new File(environment.getProperty("CSV_PATH"));
    }
}

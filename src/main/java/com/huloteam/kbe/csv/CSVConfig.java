package com.huloteam.kbe.csv;

import com.huloteam.kbe.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

@Component
@Configuration
@PropertySource({"classpath:csv.properties"})
public class CSVConfig {
    public static File getFile() {
        String fileLocation = null;

        try {
            File configFile = new File("src/main/resources/csv.properties");
            Scanner myReader = new Scanner(configFile);
            fileLocation = myReader.nextLine();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        if (Validator.isObjectNotNull(fileLocation)) {
            return new File(fileLocation);
        } else {
            return null;
        }
    }
}

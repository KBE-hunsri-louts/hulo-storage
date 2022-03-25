package com.huloteam.kbe;

import com.huloteam.kbe.csv.CSVImporter;
import com.huloteam.kbe.csv.CSVImporterImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

// https://stackoverflow.com/questions/51221777/failed-to-configure-a-datasource-url-attribute-is-not-specified-and-no-embedd
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class KbeApplication {
	public static void main(String[] args) {
		SpringApplication.run(KbeApplication.class, args);

		CSVImporter csvImporter = new CSVImporterImpl();
		csvImporter.readCSV();
	}
}

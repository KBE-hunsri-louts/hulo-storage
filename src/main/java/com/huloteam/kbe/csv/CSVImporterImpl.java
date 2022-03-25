package com.huloteam.kbe.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.huloteam.kbe.orm.DatabaseCommunicator;

public class CSVImporterImpl implements CSVImporter {
    private final static int AMOUNT_OF_CSV_ARGUMENTS = 4;
    private final static String PATH = "D://Schule/AI Studium/KBE/Vorlesung/CSV Folder/";
    private final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    private final File directoryPath = new File(PATH);
    private final DatabaseCommunicator databaseCommunicator = new DatabaseCommunicator();
    private final String[] productInformation = new String[AMOUNT_OF_CSV_ARGUMENTS];

    @Override
    public void readCSV() {
        File currentFile = getCurrentFile();

        if (currentFile != null) {
            try (Scanner scanner = new Scanner(currentFile)) {
                scanner.useDelimiter(",");

                for (int i = 0; i < AMOUNT_OF_CSV_ARGUMENTS; i++) {
                    if (scanner.hasNext()) {
                        productInformation[i] = scanner.next();
                    }
                }
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            deleteFile(currentFile);
        }

        databaseCommunicator.pushNewObjectToDatabase(productInformation[0], productInformation[1], Integer.parseInt(productInformation[2]), parseDate(productInformation[3]));
    }

    private File getCurrentFile() {
        String[] fileArray = directoryPath.list();

        if (fileArray != null) {
            if (fileArray.length > 0) {
                String fileName = directoryPath.list()[0];
                return new File(PATH + fileName);
            }
        }

        return null;
    }

    private void deleteFile(File currentFile) {
        currentFile.delete();
    }

    private Timestamp parseDate(String date) {
        try {
            return new Timestamp(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException parseException) {
            throw new IllegalArgumentException(parseException);
        }
    }
}

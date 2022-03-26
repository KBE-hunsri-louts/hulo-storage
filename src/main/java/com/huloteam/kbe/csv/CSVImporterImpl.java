package com.huloteam.kbe.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.huloteam.kbe.orm.DatabaseCommunicator;
import com.huloteam.kbe.validator.Validator;

/**
 * Reads a csv file and
 */
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

        if (Validator.isFileNotNull(currentFile)) {
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

            if (sendProductInformationToORM()) {
                deleteFile(currentFile);
            } else {
                throw new IllegalArgumentException();
            }
        }


    }

    /**
     * Opens a file which is stored on your computer
     * @return returns a file
     */
    private File getCurrentFile() {
        String[] fileArray = directoryPath.list();

        if (Validator.isArrayNotNull(fileArray)) {
            if (fileArray.length > 0) {
                String fileName = directoryPath.list()[0];
                return new File(PATH + fileName);
            }
        }

        return null;
    }

    /**
     * Deletes a file
     * @param currentFile is a file
     */
    private void deleteFile(File currentFile) {
        currentFile.delete();
    }

    /**
     * Creates a timestamp out of a string
     * @param date is a string which contains date and time information
     * @return returns a timestamp
     */
    private Timestamp parseDate(String date) {
        try {
            return new Timestamp(DATE_FORMAT.parse(date).getTime());
        } catch (ParseException parseException) {
            throw new IllegalArgumentException(parseException);
        }
    }

    /**
     * Sends information of a product to jakarta orm.
     * @return true if it succeeds and false if not
     */
    private boolean sendProductInformationToORM() {
        if (Validator.isArrayDataNotNull(productInformation) && Validator.gotStringOnlyInteger(productInformation[2])) {
            databaseCommunicator.pushNewObjectToDatabase(
                    productInformation[0],
                    productInformation[1],
                    Integer.parseInt(productInformation[2]),
                    parseDate(productInformation[3]));
            return true;
        }

        return false;
    }
}

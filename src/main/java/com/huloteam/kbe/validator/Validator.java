package com.huloteam.kbe.validator;

import org.apache.commons.lang3.math.NumberUtils;

import java.io.File;
import java.util.List;

/**
 * Checks if data matches a specific condition.
 */
public class Validator {
    /**
     * Checks if a string list is null
     * @param parameterList is a string list
     * @return true if the list is not null and false if it is null
     */
    public static boolean isStringListNotNull(List<String> parameterList) {
        return parameterList != null;
    }

    /**
     * Checks if a file is null
     * @param file
     * @return true if it's not null and false if it is null
     */
    public static boolean isFileNotNull(File file) {
        return file != null;
    }

    /**
     * Finds out if data of string array is null.
     * @param productInformation is a string array
     * @return true if the array data is not null and false if one is null
     */
    public static boolean isArrayDataNotNull(Object[] productInformation) {
        for (Object o : productInformation) {
            if (!isObjectNotNull(o)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks if a string array is null.
     * @param array
     * @return true if the array is not null, false if it is null
     */
    public static boolean isArrayNotNull(Object[] array) {
        return array != null;
    }

    /**
     * Checks if an object is null.
     * @param object
     * @return
     */
    public static boolean isObjectNotNull(Object object) {
        return object != null;
    }

    /**
     * Checks if given string contains only numbers.
     * @param value is a String.
     * @return true - only numbers / false - contains letters or symbols.
     */
    public static boolean gotStringOnlyInteger(String value) {
        return NumberUtils.isCreatable(value);
    }
}

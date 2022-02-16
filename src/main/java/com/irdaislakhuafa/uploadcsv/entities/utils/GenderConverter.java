package com.irdaislakhuafa.uploadcsv.entities.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class GenderConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        if (value.equalsIgnoreCase("l") || value.equalsIgnoreCase("p")) {
            return GenderUtils.valueOf(value.toUpperCase());
        } else {
            return GenderUtils.U;
        }
    }

}

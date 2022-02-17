package com.irdaislakhuafa.uploadcsv.entities.utils;

import com.opencsv.bean.AbstractBeanField;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvDataTypeMismatchException;

public class GenderConverter extends AbstractBeanField {

    @Override
    protected Object convert(String value) throws CsvDataTypeMismatchException, CsvConstraintViolationException {
        return (value.equalsIgnoreCase("l") ? GenderUtils.L
                : (value.equalsIgnoreCase("p") ? GenderUtils.P
                        : GenderUtils.U));
    }

}

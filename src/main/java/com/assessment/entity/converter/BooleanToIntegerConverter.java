package com.assessment.entity.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class BooleanToIntegerConverter implements AttributeConverter<Boolean, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Boolean aBoolean) {
        return aBoolean?1:0;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer aInteger) {
        return aInteger!=null && aInteger==1?true:false;
    }
}
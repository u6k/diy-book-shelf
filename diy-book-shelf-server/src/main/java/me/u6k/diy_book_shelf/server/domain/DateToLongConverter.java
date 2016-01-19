
package me.u6k.diy_book_shelf.server.domain;

import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DateToLongConverter implements AttributeConverter<Date, Long> {

    @Override
    public Long convertToDatabaseColumn(Date attribute) {
        return (attribute != null ? attribute.getTime() : null);
    }

    @Override
    public Date convertToEntityAttribute(Long dbData) {
        return (dbData != null ? new Date(dbData) : null);
    }

}

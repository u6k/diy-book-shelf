
package me.u6k.diy_book_shelf.server.domain;

import java.util.UUID;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class UuidToStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID attribute) {
        return (attribute != null ? attribute.toString() : null);
    }

    @Override
    public UUID convertToEntityAttribute(String dbData) {
        return (dbData != null ? UUID.fromString(dbData) : null);
    }

}

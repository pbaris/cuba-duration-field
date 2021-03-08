package gr.netmechanics.cuba.df.entity;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author Panos Bariamis (pbaris)
 */
@Converter
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration attribute) {
        return attribute == null ? null : attribute.toMillis();
    }

    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : Duration.of(dbData, ChronoUnit.MILLIS);
    }
}

package com.healthbridge.entity;

import java.sql.Time;
import java.time.Duration;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class DurationAttributeConverter implements AttributeConverter<Duration, Time> {

  @Override
  public Time convertToDatabaseColumn(Duration duration) {
    if (duration == null) {
    return null;
  }
    return Time.valueOf(duration.toString());
  }

  @Override
  public Duration convertToEntityAttribute(Time time) {
    if (time == null ) {
    return null;
  }
    return Duration.parse(time.toString());
  }

}

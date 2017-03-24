package com.team.icr.common.db;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(final LocalDateTime dateTime) {
		return dateTime == null ? null : Timestamp.valueOf(dateTime);
	}

	@Override
	public LocalDateTime convertToEntityAttribute(final Timestamp sqlTimestamp) {
		return sqlTimestamp == null ? null : sqlTimestamp.toLocalDateTime();
	}
}

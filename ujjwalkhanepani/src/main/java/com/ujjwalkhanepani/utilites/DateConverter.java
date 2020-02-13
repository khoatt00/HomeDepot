package com.ujjwalkhanepani.utilites;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateConverter extends StdDeserializer<Date> {
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public DateConverter() {
        this(null);
    }

    public DateConverter(Class<?> clazz) {
        super(clazz);
    }


    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String date = jsonParser.getText();
        log.info("Parsing date {}", date);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error("Error occured while parsing date. Here is the exception ", e);
            return null;
        }
    }
}

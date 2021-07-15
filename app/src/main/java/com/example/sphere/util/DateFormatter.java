package com.example.sphere.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatter {
    public String convertDate(String dt) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat fmt2 = new SimpleDateFormat("dd - mm - yyyy | HH:ss", new Locale("ID"));
        try {
            Date date = fmt.parse(dt);
            return fmt2.format(date);
        } catch(ParseException pe) {
            return "Date";
        }
    }
}
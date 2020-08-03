package com.twu.refactoring;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

import static java.lang.String.format;

public class DateParser {
    private final String dateAndTimeString;
    private static final HashMap<String, TimeZone> KNOWN_TIME_ZONES = new HashMap<String, TimeZone>();

    enum DateFormation {

        YEAR("Year", 0, 4, 2000, 2012), MONTH("Month", 5, 7, 1, 12), DATE("Date", 8, 10, 1, 31), HOUR("Hour", 11, 13, 0, 23), MINUTE(
                "Minute", 14, 16, 0, 59);

        private String name;
        private int stringStart;
        private int stringEnd;
        private int min;
        private int max;
        private int value = 0;

        DateFormation(String name, int stringStart, int stringEnd, int min, int max) {
            this.name = name;
            this.stringStart = stringStart;
            this.stringEnd = stringEnd;
            this.min = min;
            this.max = max;
        }

        void Parse(String dateAndTimeString) {
            int date;
            try {
                String yearString = dateAndTimeString.substring(this.stringStart, this.stringEnd);
                date = Integer.parseInt(yearString);
            } catch (StringIndexOutOfBoundsException e) {
                throw new IllegalArgumentException(format("%s%s%d%s", this.name, " string is less than ",
                        this.stringEnd - this.stringStart, " characters"));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(format("%s%s", this.name, " is not an integer"));
            }
            if (date < this.min || date > this.max)
                throw new IllegalArgumentException(format("%s%s%d%s%d", this.name, " cannot be less than ", this.min, " or " +
                        "more than ", this.max));
            this.value = date;
        }

        int getValue(){
            return value;
        }
    }

    static {
        KNOWN_TIME_ZONES.put("UTC", TimeZone.getTimeZone("UTC"));
    }

    /**
     * Takes a date in ISO 8601 format and returns a date
     *
     * @param dateAndTimeString - should be in format ISO 8601 format
     *                          examples -
     *                          2012-06-17 is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17TZ is 17th June 2012 - 00:00 in UTC TimeZone
     *                          2012-06-17T15:00Z is 17th June 2012 - 15:00 in UTC TimeZone
     */
    public DateParser(String dateAndTimeString) {
        this.dateAndTimeString = dateAndTimeString;
    }

    public Date parse() {
        for (int i = 0; i < 3; i++) {
            DateFormation.values()[i].Parse(dateAndTimeString);
        }
        if (!dateAndTimeString.substring(11, 12).equals("Z")) {
            for (int i = 3; i < 5; i++) {
                DateFormation.values()[i].Parse(dateAndTimeString);
            }
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(DateFormation.YEAR.getValue(), DateFormation.MONTH.getValue() - 1, DateFormation.DATE.getValue(),
                DateFormation.HOUR.getValue(), DateFormation.MINUTE.getValue(), 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

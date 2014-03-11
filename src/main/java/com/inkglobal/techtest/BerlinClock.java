package com.inkglobal.techtest;

import org.apache.commons.lang3.StringUtils;;

public class BerlinClock {

    private static final String OFF = "O";
    private static final String RED = "R";
    private static final String YELLOW = "Y";
    private static final String NEWLINE = "\n";
    private int hours;
    private int minutes;
    private int seconds;

    public String getTime() {
        StringBuilder s = new StringBuilder();
        s.append(getSeconds());
        s.append(getHours());
        s.append(getMinutes());
        return s.toString();
    }

    public void setTime(String t) {
        String[] pieces = t.split(":");
        setHours(pieces[0]);
        setMinutes(pieces[1]);
        setSeconds(pieces[2]);
    }

    public String getSeconds() {
        if(seconds % 2 == 0) {
            return YELLOW + NEWLINE;
        } else {
            return OFF + NEWLINE;
        }
    }

    public String getHours() {
        int quotient = hours / 5;
        int remainder = hours % 5;
        StringBuilder s = new StringBuilder();
            s.append(rowOfFour(quotient));
            s.append(NEWLINE);
            s.append(rowOfFour(remainder));
            s.append(NEWLINE);
        return s.toString();
    }

    public String getMinutes() {
        int quotient = minutes / 5;
        int remainder = minutes % 5;
        StringBuilder s = new StringBuilder();
            s.append(rowOfEleven(quotient));
            s.append(NEWLINE);
            s.append(rowOfFour(remainder).replace(RED, YELLOW));
            s.append(NEWLINE);
        return s.toString();
    }

    private void setSeconds(String s) {
        int i = Integer.parseInt(s);
        if(i >= 0 && i <= 59) {
            seconds = i;
        } else {
            throw new IllegalArgumentException(
                    "seconds must be between 0 and 59"
            );
        }
    }

    private void setMinutes(String s) {
        int i = Integer.parseInt(s);
        if(i >= 0 && i <= 59) {
            minutes = i;
        } else {
            throw new IllegalArgumentException(
                    "minutes must be between 0 and 59"
            );
        }
    }

    private void setHours(String s) {
        int i = Integer.parseInt(s);
        if(i >= 0 && i <= 24) {
            hours = i;
        } else {
            throw new IllegalArgumentException(
                    "hours must be between 0 and 24"
            );
        }
    }

    private String rowOfFour(int value) {
        String[] off = {OFF, OFF, OFF, OFF};
        for(int i = 0; i < value; i++) {
            off[i] = RED;
        }
        return StringUtils.join(off, "");
    }

    private String rowOfEleven(int value) {
        String[] off = {OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF, OFF};
        for(int i = 0; i < value; i++) {
            if((i + 1) % 3 == 0) {
                off[i] = RED;
            } else {
                off[i] = YELLOW;
            }
        }
        return StringUtils.join(off, "");
    }

}

package com.example.smartalarm;

import java.io.Serializable;
import java.util.Calendar;

class Event implements Serializable {
    public Event() {
        name = null;
        calendar = null;
        action = null;
    }

    public Event(String name, Calendar calendar, String action) {
        this.name = name;
        this.calendar = calendar;
        this.action = action;
    }

    public String name;
    public Calendar calendar;
    public String action;
    public boolean activated;
}


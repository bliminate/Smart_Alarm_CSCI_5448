package com.example.smartalarm.database;

import androidx.room.TypeConverter;

import java.util.Calendar;

public class CalendarTypeConverter {
   @TypeConverter
   public Long calendarToTimestamp(Calendar c){
      if(c == null){ return null; }
      else{
         return c.getTimeInMillis();
      }
   }

   @TypeConverter
   public Calendar fromTimestamp(Long t){
      Calendar c = Calendar.getInstance();
      c.setTimeInMillis(t);
      return c;
   }
}

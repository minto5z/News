package com.example.news.data;


import androidx.room.TypeConverter;

import com.example.news.api.Source;

public class SourceConverter {
    @TypeConverter
    public static String sourceToString(Source source) {
        return source.getName();
    }

    @TypeConverter
    public static Source stringToSource(String string) {
        Source source = new Source();
        source.setName(string);
        return source;
    }
}

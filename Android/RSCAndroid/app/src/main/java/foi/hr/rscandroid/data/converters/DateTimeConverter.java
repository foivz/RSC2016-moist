package foi.hr.rscandroid.data.converters;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.lang.reflect.Type;

public class DateTimeConverter implements JsonDeserializer<DateTime> {

    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    @Override
    public DateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String dateString = "";

        try {
            dateString = json.getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (dateString.length() == 10) {
            return dateFormatter.parseDateTime(dateString);
        }

        return DateTime.now();
    }
}

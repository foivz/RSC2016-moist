package foi.hr.rscandroid.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EventsResponse {

    @SerializedName("quizzes")
    private ArrayList<Event> events;

    public ArrayList<Event> getEvents() {
        return events;
    }
}

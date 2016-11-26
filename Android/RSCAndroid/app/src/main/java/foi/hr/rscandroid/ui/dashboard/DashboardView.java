package foi.hr.rscandroid.ui.dashboard;

import java.util.ArrayList;

import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.ui.BaseMvp;

public interface DashboardView extends BaseMvp.View {

    void onEventsReceived(ArrayList<Event> events);
}

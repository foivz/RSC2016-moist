package foi.hr.rscandroid.ui.dashboard;

import android.location.Location;
import android.text.TextUtils;

import java.util.ArrayList;

import foi.hr.rscandroid.data.interactors.FetchEventsInteractor;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.data.models.EventsResponse;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class DashboardPresenter {

    private DashboardView view;

    private FetchEventsInteractor fetchEventsInteractor;

    private ArrayList<Event> events;

    public DashboardPresenter(DashboardView view, FetchEventsInteractor fetchEventsInteractor) {
        this.view = view;
        this.fetchEventsInteractor = fetchEventsInteractor;
    }

    public void init() {
        view.showProgress();
        fetchEventsInteractor.fetchEvents(eventListener);
    }

    private void processEvents() {
        if (!TextUtils.isEmpty(SharedPrefsHelper.getSharedPrefsString(SharedPrefsHelper.KEY_LAT))
                && !TextUtils.isEmpty(SharedPrefsHelper.getSharedPrefsString(SharedPrefsHelper.KEY_LNG))) {
            processDistance();
        } else {
            view.fetchCurrentLocation();
        }
        processModerator();
        view.onEventsReceived(events);
    }

    public void onLocationRetrieved() {
        processEvents();
    }

    private void processDistance() {
        double lat;
        double lng;
        try {
            lat = Double.parseDouble(SharedPrefsHelper.getSharedPrefsString(SharedPrefsHelper.KEY_LAT));
            lng = Double.parseDouble(SharedPrefsHelper.getSharedPrefsString(SharedPrefsHelper.KEY_LNG));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }

        Location eventLocation = new Location("");
        Location currentLocation = new Location("");
        currentLocation.setLatitude(lat);
        currentLocation.setLongitude(lng);
        for (Event event : events) {
            eventLocation.setLatitude(event.getLatitude());
            eventLocation.setLongitude(event.getLongitude());
            event.setDistanceFromCurrentLocation(eventLocation.distanceTo(currentLocation));
        }
    }

    private void processModerator() {
        int id = SharedPrefsHelper.getSharedPrefsInt(SharedPrefsHelper.USER_ID);
        for (Event event : events) {
            if (event.getId() == id) {
                event.setUserModerator(true);
            }
        }
    }

    private Listener<BaseResponse<EventsResponse>> eventListener = new Listener<BaseResponse<EventsResponse>>() {
        @Override
        public void onSuccess(BaseResponse<EventsResponse> response) {
            view.hideProgress();
            events = response.getResponse().getEvents();
            processEvents();
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };
}

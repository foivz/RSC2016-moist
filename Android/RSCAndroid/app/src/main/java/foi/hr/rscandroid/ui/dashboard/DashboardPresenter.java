package foi.hr.rscandroid.ui.dashboard;

import foi.hr.rscandroid.data.interactors.FetchEventsInteractor;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.EventsResponse;
import foi.hr.rscandroid.ui.shared.Listener;

public class DashboardPresenter {

    private DashboardView view;

    private FetchEventsInteractor fetchEventsInteractor;

    public DashboardPresenter(DashboardView view, FetchEventsInteractor fetchEventsInteractor) {
        this.view = view;
        this.fetchEventsInteractor = fetchEventsInteractor;
    }

    public void init() {
        view.showProgress();
        fetchEventsInteractor.fetchEvents(eventListener);
    }

    private Listener<BaseResponse<EventsResponse>> eventListener = new Listener<BaseResponse<EventsResponse>>() {
        @Override
        public void onSuccess(BaseResponse<EventsResponse> response) {
            view.hideProgress();
            view.onEventsReceived(response.getResponse().getEvents());
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };
}

package foi.hr.rscandroid.data.interactors;

import android.support.annotation.Nullable;

import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.EventsResponse;
import foi.hr.rscandroid.data.networking.ApiService;
import foi.hr.rscandroid.data.networking.BaseCallback;
import foi.hr.rscandroid.ui.shared.Listener;
import retrofit2.Response;

public class FetchEventsInteractor {

    private ApiService apiService;

    private Listener<BaseResponse<EventsResponse>> listener;

    public FetchEventsInteractor(ApiService apiService) {
        this.apiService = apiService;
    }

    public void fetchEvents(Listener<BaseResponse<EventsResponse>> listener) {
        this.listener = listener;
        apiService.fetchEvents().enqueue(callback);
    }

    private BaseCallback<BaseResponse<EventsResponse>> callback = new BaseCallback<BaseResponse<EventsResponse>>() {

        @Override
        public void onSuccess(BaseResponse<EventsResponse> body, Response<BaseResponse<EventsResponse>> response) {
            listener.onSuccess(body);
        }

        @Override
        public void onUnknownError(@Nullable String error) {
            listener.onError(error);
        }
    };
}

package foi.hr.rscandroid.ui.qr;

import foi.hr.rscandroid.data.interactors.BarcodeInteractor;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.TeamResponse;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class ScannerPresenter {

    private ScannerView view;

    private BarcodeInteractor interactor;

    private boolean processing;

    public ScannerPresenter(ScannerView view, BarcodeInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void proccessBarcode(String value) {
        if (processing) {
            return;
        }

        view.showProgress();
        processing = true;

        int teamId;
        try {
            String[] split = value.split("=");
            teamId = Integer.parseInt(split[1]);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            view.hideProgress();
            view.showMessage("Error while processing barcode");
            return;
        }

        interactor.processBarcode(teamId, SharedPrefsHelper.getSharedPrefsInt(SharedPrefsHelper.USER_ID), listener);
    }

    private Listener<BaseResponse<TeamResponse>> listener = new Listener<BaseResponse<TeamResponse>>() {
        @Override
        public void onSuccess(BaseResponse<TeamResponse> response) {
            view.hideProgress();
            view.showNewTeam(response.getResponse().getTeam());

            // If it is a success, there will be no more processing so no need to reset the flag
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
            processing = false;
        }
    };
}

package foi.hr.rscandroid.ui.registration;


import android.text.TextUtils;
import android.util.Log;

import foi.hr.rscandroid.data.interactors.RegistrationInteractor;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.shared.Listener;

public class RegistrationPresenter {

    private RegistrationView view;

    private RegistrationInteractor interactor;

    private UserRequest user;

    public RegistrationPresenter(RegistrationView view, RegistrationInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void initModels(User user) {
        this.user = new UserRequest();
        this.user.setUserData(user.getUserData());
    }

    public void sendNickname(String nickname) {
        view.showProgress();
        if (!TextUtils.isEmpty(nickname) && user != null) {
            user.getUserData().setNickname(nickname);
            BaseRequest<UserRequest> br = new BaseRequest<>();
            br.setRequest(user);
            interactor.sendNickname(br, nicknameListener);
        } else {
            view.hideProgress();
            view.showEmptyNicknameError();
        }
    }

    private Listener<Void> nicknameListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            view.hideProgress();
            view.proceedToDashboard(user);
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            Log.e("NICKNAME", error);
            view.showUsernameTakenError();
        }
    };
}

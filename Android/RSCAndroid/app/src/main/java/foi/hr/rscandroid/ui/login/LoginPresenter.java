package foi.hr.rscandroid.ui.login;


import android.text.TextUtils;

import foi.hr.rscandroid.data.interactors.LoginInteractor;
import foi.hr.rscandroid.data.models.BaseResponse;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.ui.shared.Listener;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class LoginPresenter {

    public static final String TOKEN = "token";

    private LoginView view;

    private LoginInteractor interactor;

    public LoginPresenter(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void sendFbAuthToApi(String token) {
        interactor.authorizeFb(token, facebookLoginListener);
    }

    private Listener<User> facebookLoginListener = new Listener<User>() {
        @Override
        public void onSuccess(User facebookLoginModel) {
            storeTokenToPrefs(facebookLoginModel.getToken());
            view.hideProgress();
            if (facebookLoginModel.getUserData().isRegistered()) {
                view.proceedToMain();
            } else {
                view.proceedToRegistration();
            }
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };

    private void storeTokenToPrefs(String token) {
        SharedPrefsHelper.setSharedPrefsString(TOKEN, token);
    }

    void login(String email, String pw) {
        if (TextUtils.isEmpty(email)) {
            view.setEmailError("Email cannot be empty");
            return;
        }

        if (TextUtils.isEmpty(pw)) {
            view.setPwError("Password cannot be empty");
            return;
        }

        view.showProgress();
        interactor.loginNormally(email, pw);
    }

    public void sendGoogleAuthToAPI(String idToken) {
        view.showProgress();
        interactor.loginGoogle(idToken, googleLoginListener);
    }

    private Listener<BaseResponse<User>> googleLoginListener = new Listener<BaseResponse<User>>() {
        @Override
        public void onSuccess(BaseResponse<User> userBaseResponse) {
            view.hideProgress();
            if (userBaseResponse.getResponse().getUserData().isRegistered()) {
                view.proceedToMain();
            } else {
                view.proceedToRegistration();
            }
        }

        @Override
        public void onError(String error) {
            view.hideProgress();
            view.showMessage(error);
        }
    };
}

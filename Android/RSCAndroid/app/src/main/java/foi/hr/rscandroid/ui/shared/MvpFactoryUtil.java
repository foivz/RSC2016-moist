package foi.hr.rscandroid.ui.shared;


import foi.hr.rscandroid.RSCApplication;
import foi.hr.rscandroid.data.interactors.LoginInteractor;
import foi.hr.rscandroid.data.interactors.RegistrationInteractor;
import foi.hr.rscandroid.ui.login.LoginPresenter;
import foi.hr.rscandroid.ui.login.LoginView;
import foi.hr.rscandroid.ui.registration.RegistrationPresenter;
import foi.hr.rscandroid.ui.registration.RegistrationView;

public class MvpFactoryUtil {

    public static LoginPresenter getPresenter(LoginView view) {
        return new LoginPresenter(view, new LoginInteractor(RSCApplication.getApiService()));
    }

    public static RegistrationPresenter getPresenter(RegistrationView view) {
        return new RegistrationPresenter(view, new RegistrationInteractor(RSCApplication.getApiService()));
    }

}
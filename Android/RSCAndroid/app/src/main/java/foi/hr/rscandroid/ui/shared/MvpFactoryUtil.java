package foi.hr.rscandroid.ui.shared;


import foi.hr.rscandroid.RSCApplication;
import foi.hr.rscandroid.data.interactors.LoginInteractor;
import foi.hr.rscandroid.ui.login.LoginPresenter;
import foi.hr.rscandroid.ui.login.LoginView;

public class MvpFactoryUtil {

    public static LoginPresenter getPresenter(LoginView view){
        return new LoginPresenter(view, new LoginInteractor(RSCApplication.getApiService()));
    }

}

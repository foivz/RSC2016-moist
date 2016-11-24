package foi.hr.rscandroid.ui.login;


import foi.hr.rscandroid.data.interactors.LoginInteractor;

public class LoginPresenter {

    private LoginView view;
    private LoginInteractor interactor;

    public LoginPresenter(LoginView view, LoginInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }
}

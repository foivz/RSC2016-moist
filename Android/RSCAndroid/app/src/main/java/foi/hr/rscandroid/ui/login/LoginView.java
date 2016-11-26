package foi.hr.rscandroid.ui.login;


import foi.hr.rscandroid.ui.BaseMvp;

public interface LoginView extends BaseMvp.View {

    void proceedToMain();

    void setEmailError(String s);

    void setPwError(String s);

    void proceedToUserDetails();
}

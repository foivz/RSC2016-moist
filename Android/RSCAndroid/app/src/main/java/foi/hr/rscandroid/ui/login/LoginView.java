package foi.hr.rscandroid.ui.login;


import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseMvp;

public interface LoginView extends BaseMvp.View {

    void proceedToMain(UserRequest response);

    void setEmailError(String s);

    void setPwError(String s);

    void proceedToRegistration(User userData);
}

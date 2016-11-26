package foi.hr.rscandroid.ui.registration;


import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.ui.BaseMvp;

public interface RegistrationView extends BaseMvp.View {

    void showEmptyNicknameError();

    void proceedToDashboard(User user);

    void showUsernameTakenError();
}

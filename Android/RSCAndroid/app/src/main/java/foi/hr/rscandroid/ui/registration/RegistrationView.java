package foi.hr.rscandroid.ui.registration;


import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseMvp;

public interface RegistrationView extends BaseMvp.View {

    void showEmptyNicknameError();

    void proceedToDashboard(UserRequest user);

    void showUsernameTakenError();
}

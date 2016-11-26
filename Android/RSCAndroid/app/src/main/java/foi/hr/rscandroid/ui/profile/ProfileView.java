package foi.hr.rscandroid.ui.profile;


import foi.hr.rscandroid.ui.BaseMvp;

public interface ProfileView extends BaseMvp.View {

    void onNickUpdateSuccess();

    void onNickUpdateError(String error);
}

package foi.hr.rscandroid.ui.profile;


import foi.hr.rscandroid.data.interactors.ProfileInteractor;
import foi.hr.rscandroid.data.models.BaseRequest;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserData;
import foi.hr.rscandroid.ui.shared.Listener;

public class ProfilePresenter {

    private ProfileView view;

    private ProfileInteractor interactor;

    public ProfilePresenter(ProfileView view, ProfileInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void updateNickname(String newNickname, int id) {
        view.showProgress();
        User user = new User();
        UserData userData = new UserData();
        userData.setNickname(newNickname);
        user.setUserData(userData);
        BaseRequest<User> baseRequest = new BaseRequest<>();
        baseRequest.setRequest(user);
        interactor.updateNickname(baseRequest, id, updateNicknameListener);
    }

    private Listener<Void> updateNicknameListener = new Listener<Void>() {
        @Override
        public void onSuccess(Void aVoid) {
            view.hideProgress();
            view.onNickUpdateSuccess();
        }

        @Override
        public void onError(String error) {
            view.onNickUpdateError(error);
        }
    };
}

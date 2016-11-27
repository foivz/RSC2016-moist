package foi.hr.rscandroid.ui.shared;


import foi.hr.rscandroid.RSCApplication;
import foi.hr.rscandroid.data.interactors.BarcodeInteractor;
import foi.hr.rscandroid.data.interactors.EditAnswerInteractor;
import foi.hr.rscandroid.data.interactors.EventDetailsInteractor;
import foi.hr.rscandroid.data.interactors.FetchEventsInteractor;
import foi.hr.rscandroid.data.interactors.GameInteractor;
import foi.hr.rscandroid.data.interactors.LoginInteractor;
import foi.hr.rscandroid.data.interactors.MultipleAnswerInteractor;
import foi.hr.rscandroid.data.interactors.ProfileInteractor;
import foi.hr.rscandroid.data.interactors.RegistrationInteractor;
import foi.hr.rscandroid.data.interactors.SingleAnswerInteractor;
import foi.hr.rscandroid.data.interactors.TeamDetailsInteractor;
import foi.hr.rscandroid.data.interactors.TeamInteractor;
import foi.hr.rscandroid.ui.dashboard.DashboardPresenter;
import foi.hr.rscandroid.ui.dashboard.DashboardView;
import foi.hr.rscandroid.ui.details.EventDetailsPresenter;
import foi.hr.rscandroid.ui.details.EventDetailsView;
import foi.hr.rscandroid.ui.game.GamePresenter;
import foi.hr.rscandroid.ui.game.GameView;
import foi.hr.rscandroid.ui.game.fillanswer.EditAnswerPresenter;
import foi.hr.rscandroid.ui.game.fillanswer.EditAnswerView;
import foi.hr.rscandroid.ui.game.multipleanswer.MultipleAnswerPresenter;
import foi.hr.rscandroid.ui.game.multipleanswer.MultipleAnswerView;
import foi.hr.rscandroid.ui.game.singleanswer.SingleAnswerPresenter;
import foi.hr.rscandroid.ui.game.singleanswer.SingleAnswerView;
import foi.hr.rscandroid.ui.login.LoginPresenter;
import foi.hr.rscandroid.ui.login.LoginView;
import foi.hr.rscandroid.ui.profile.ProfilePresenter;
import foi.hr.rscandroid.ui.profile.ProfileView;
import foi.hr.rscandroid.ui.qr.ScannerPresenter;
import foi.hr.rscandroid.ui.qr.ScannerView;
import foi.hr.rscandroid.ui.registration.RegistrationPresenter;
import foi.hr.rscandroid.ui.registration.RegistrationView;
import foi.hr.rscandroid.ui.tdetails.TeamDetailsPresenter;
import foi.hr.rscandroid.ui.tdetails.TeamDetailsView;
import foi.hr.rscandroid.ui.teams.TeamPresenter;
import foi.hr.rscandroid.ui.teams.TeamView;

public class MvpFactoryUtil {

    public static LoginPresenter getPresenter(LoginView view) {
        return new LoginPresenter(view, new LoginInteractor(RSCApplication.getApiService()));
    }

    public static RegistrationPresenter getPresenter(RegistrationView view) {
        return new RegistrationPresenter(view, new RegistrationInteractor(RSCApplication.getApiService()));
    }

    public static TeamPresenter getPresenter(TeamView view) {
        return new TeamPresenter(view, new TeamInteractor(RSCApplication.getApiService()));
    }

    public static DashboardPresenter getPresenter(DashboardView view) {
        return new DashboardPresenter(view, new FetchEventsInteractor(RSCApplication.getApiService()));
    }

    public static ProfilePresenter getPresenter(ProfileView view) {
        return new ProfilePresenter(view, new ProfileInteractor(RSCApplication.getApiService()));
    }

    public static TeamDetailsPresenter getPresenter(TeamDetailsView view) {
        return new TeamDetailsPresenter(view, new TeamDetailsInteractor(RSCApplication.getApiService()));
    }

    public static GamePresenter getPresenter(GameView view) {
        return new GamePresenter(view, new GameInteractor(RSCApplication.getApiService()));
    }

    public static SingleAnswerPresenter getPresenter(SingleAnswerView view) {
        return new SingleAnswerPresenter(view, new SingleAnswerInteractor(RSCApplication.getApiService()));
    }

    public static MultipleAnswerPresenter getPresenter(MultipleAnswerView view) {
        return new MultipleAnswerPresenter(view, new MultipleAnswerInteractor(RSCApplication.getApiService()));
    }

    public static EditAnswerPresenter getPresenter(EditAnswerView view) {
        return new EditAnswerPresenter(view, new EditAnswerInteractor(RSCApplication.getApiService()));

    }

    public static EventDetailsPresenter getPresenter(EventDetailsView view) {
        return new EventDetailsPresenter(view, new EventDetailsInteractor(RSCApplication.getApiService()));
    }

    public static ScannerPresenter getPresenter(ScannerView view) {
        return new ScannerPresenter(view, new BarcodeInteractor(RSCApplication.getApiService()));

    }

}

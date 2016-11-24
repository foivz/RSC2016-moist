package foi.hr.rscandroid.data.push;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class RSCFirebaseInstanceIdService extends FirebaseInstanceIdService {

    public static final String FIREBASE_TOKEN = "FirebaseToken";

    public RSCFirebaseInstanceIdService() {
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String refreshedToken) {
        SharedPrefsHelper.clearSingleSharedPrefsItem(FIREBASE_TOKEN);
        SharedPrefsHelper.setSharedPrefsString(FIREBASE_TOKEN, refreshedToken);
    }
}

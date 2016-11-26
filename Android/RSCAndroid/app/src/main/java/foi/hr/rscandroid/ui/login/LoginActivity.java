package foi.hr.rscandroid.ui.login;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.DashboardActivity;
import foi.hr.rscandroid.ui.registration.RegistrationActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class LoginActivity extends BaseActivity implements LoginView, GoogleApiClient.OnConnectionFailedListener {

    public static final String EMAIL = "email";

    private static final int RC_SIGN_IN = 420;

    public static final String EXTRA_USER_DATA = "EXTRA_USER_DATA";

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginButton facebookLoginButton;

    private SignInButton googleLoginButton;

    private GoogleApiClient googleApiClient;

    private LoginPresenter presenter;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);
        initFb();
        initGoogle();
        initUi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void proceedToMain(UserRequest response) {
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra(EXTRA_USER_DATA, response);
        startActivity(intent);
    }

    @Override
    public void setEmailError(String s) {
        etEmail.setError(s);
    }

    @Override
    public void setPwError(String s) {
        etPassword.setError(s);
    }

    @Override
    public void proceedToRegistration(User userData) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra(EXTRA_USER_DATA, userData);
        startActivity(intent);
    }

    private void initUi() {

    }


    private void initGoogle() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestServerAuthCode("24623373370-uuc4qcdodj93e40obdl21pf29780v3m5.apps.googleusercontent.com")
                .requestIdToken("24623373370-uuc4qcdodj93e40obdl21pf29780v3m5.apps.googleusercontent.com")
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        googleLoginButton = (SignInButton) findViewById(R.id.btn_google_sign_in);
        googleLoginButton.setSize(SignInButton.SIZE_WIDE);

        googleLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    private void initFb() {
        facebookLoginButton = (LoginButton) findViewById(R.id.login_button);
        facebookLoginButton.setReadPermissions(EMAIL);

        callbackManager = CallbackManager.Factory.create();

        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("FB", loginResult.getAccessToken().getToken());
                presenter.sendFbAuthToApi(loginResult.getAccessToken().getToken());

                showProgress();
            }

            @Override
            public void onCancel() {
                Log.e("succ", "cancel");
            }

            @Override
            public void onError(FacebookException error) {
                showMessage(error.getMessage());
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("G+SIGNINSTATUS", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.e("GTOKEN", acct.getIdToken());
            presenter.sendGoogleAuthToAPI(acct.getIdToken());
        } else {
            showMessage("Failed to authenticate g+ account");
        }
    }
}

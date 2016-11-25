package foi.hr.rscandroid.ui.login;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.main.MainActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class LoginActivity extends BaseActivity implements LoginView {

    public static final String EMAIL = "email";

    @BindView(R.id.et_email)
    EditText etEmail;

    @BindView(R.id.et_password)
    EditText etPassword;

    private LoginButton loginButton;

    private LoginPresenter presenter;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);
        initUi();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void proceedToMain() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void setEmailError(String s) {
        etEmail.setError(s);
    }

    @Override
    public void setPwError(String s) {
        etPassword.setError(s);
    }

    private void initUi() {
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(EMAIL);

        callbackManager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
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

    @OnClick(R.id.btn_login)
    public void normalLoginButtonClicked() {
        presenter.login(etEmail.getText().toString(), etPassword.getText().toString());
    }

}

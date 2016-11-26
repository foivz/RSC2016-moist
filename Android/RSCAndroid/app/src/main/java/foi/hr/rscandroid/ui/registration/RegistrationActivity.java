package foi.hr.rscandroid.ui.registration;

import com.rengwuxian.materialedittext.MaterialEditText;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.User;
import foi.hr.rscandroid.data.models.UserData;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.login.LoginActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class RegistrationActivity extends BaseActivity implements RegistrationView {

    @BindView(R.id.et_nickname)
    MaterialEditText etNickname;

    private RegistrationPresenter presenter;

    private UserData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);

        User user = (User) getIntent().getExtras().getSerializable(LoginActivity.EXTRA_USER_DATA);
        if (user != null) {
            userData = user.getUserData();
            presenter.initModels(user);
        }
    }

    @OnClick(R.id.btn_next)
    public void nextClicked() {
        presenter.sendNickname(etNickname.getText().toString());
    }

    @Override
    public void showEmptyNicknameError() {
        etNickname.setError("Nickname can't be empty");
    }

    @Override
    public void proceedToDashboard(User user) {
        
    }

    @Override
    public void showUsernameTakenError() {
        etNickname.setError("That nickname is already taken");
    }
}

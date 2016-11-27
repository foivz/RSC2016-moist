package foi.hr.rscandroid.ui.dashboard.profile;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseFragment;
import foi.hr.rscandroid.ui.dashboard.DashboardActivity;
import foi.hr.rscandroid.ui.login.LoginActivity;
import foi.hr.rscandroid.ui.profile.ProfilePresenter;
import foi.hr.rscandroid.ui.profile.ProfileView;
import foi.hr.rscandroid.ui.shared.DisplayHelper;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.teams.TeamActivity;

public class ProfileFragment extends BaseFragment implements ProfileView {


    public static final String EXTRA_COLOR = "color";

    public static final String EXTRA_TEAMS = "EXTRA_TEAMS";

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    @BindView(R.id.tv_nickname_data)
    TextView tvNicknameData;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_email_data)
    TextView tvEmailData;

    @BindView(R.id.et_nickname_data)
    EditText etNicknameData;

    @BindView(R.id.iv_edit)
    ImageView ivEdit;

    @BindView(R.id.btn_accept)
    ImageView btnAccept;

    @BindView(R.id.btn_decline)
    ImageView btnDecline;

    @BindView(R.id.confirmation_container)
    LinearLayout confirmationContainer;

    @BindView(R.id.tv_nickname)
    TextView tvNickname;

    private UserRequest user;

    private ProfilePresenter presenter;

    public static ProfileFragment newInstance(UserRequest user) {
        Bundle args = new Bundle();
        args.putSerializable(LoginActivity.EXTRA_USER_DATA, user);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragment newInstance(int color) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_COLOR, color);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static ProfileFragment newInstance(int color, UserRequest user) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_COLOR, color);
        args.putSerializable(LoginActivity.EXTRA_USER_DATA, user);
        ProfileFragment fragment = new ProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        presenter = MvpFactoryUtil.getPresenter(this);
        user = (UserRequest) getArguments().getSerializable(LoginActivity.EXTRA_USER_DATA);
        initUi();

        return view;
    }

    private void initUi() {
        Glide.with(getBaseActivity())
                .load(user.getUserData().getAvatar())
                .into(ivAvatar);

        tvName.setText(user.getUserData().getName());
        tvNicknameData.setText(user.getUserData().getNickname());
        tvEmailData.setText(user.getUserData().getEmail());
    }

    @OnClick(R.id.btn_view_teams)
    public void viewTeamsBtnClicked() {
        Intent intent = new Intent(getBaseActivity(), TeamActivity.class);
        intent.putExtra(EXTRA_TEAMS, user.getUserData().getTeams());
        startActivity(intent);
    }

    @OnClick(R.id.iv_edit)
    public void onEditClicked() {
        confirmationContainer.setVisibility(View.VISIBLE);
        ivEdit.setVisibility(View.GONE);
        tvNickname.setVisibility(View.GONE);
        tvNicknameData.setVisibility(View.GONE);
        etNicknameData.setVisibility(View.VISIBLE);
        etNicknameData.setText(tvNicknameData.getText());
    }

    @OnClick(R.id.btn_accept)
    public void onAcceptClicked() {
        if (etNicknameData.hasFocus()) {
            etNicknameData.clearFocus();
            DisplayHelper.hideKeyboard(etNicknameData);
        }
        ivEdit.setVisibility(View.VISIBLE);
        confirmationContainer.setVisibility(View.GONE);
        tvNicknameData.setVisibility(View.VISIBLE);
        tvNickname.setVisibility(View.VISIBLE);
        tvNicknameData.setText(etNicknameData.getText());
        etNicknameData.setVisibility(View.GONE);
        user.getUserData().setNickname(etNicknameData.getText().toString());
        presenter.updateNickname(etNicknameData.getText().toString(), user.getUserData().getId());
    }

    @OnClick(R.id.btn_decline)
    public void onDeclineClicked() {
        if (etNicknameData.hasFocus()) {
            etNicknameData.clearFocus();
            DisplayHelper.hideKeyboard(etNicknameData);
        }
        ivEdit.setVisibility(View.VISIBLE);
        confirmationContainer.setVisibility(View.GONE);
        tvNickname.setVisibility(View.VISIBLE);
        tvNicknameData.setVisibility(View.VISIBLE);
        etNicknameData.setVisibility(View.GONE);
    }


    @Override
    public void onNickUpdateSuccess() {
        user.getUserData().setNickname(etNicknameData.getText().toString());

        ((DashboardActivity) getBaseActivity()).updateUser(user);

        Toast.makeText(getBaseActivity(), "Nickname successfully updated", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNickUpdateError(String error) {
        Toast.makeText(getBaseActivity(), "Nickname update failed", Toast.LENGTH_SHORT).show();

    }
}

package foi.hr.rscandroid.ui.dashboard.profile;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseFragment;
import foi.hr.rscandroid.ui.login.LoginActivity;
import foi.hr.rscandroid.ui.teams.TeamActivity;

public class ProfileFragment extends BaseFragment {


    public static final String EXTRA_COLOR = "color";

    public static final String EXTRA_TEAMS = "EXTRA_TEAMS";

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    @BindView(R.id.tv_nickname_data)
    TextView tvNicknameData;

    @BindView(R.id.tv_name)
    TextView tvName;

    private UserRequest user;

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
        user = (UserRequest) getArguments().getSerializable(LoginActivity.EXTRA_USER_DATA);
        initUi();

        return view;
    }

    private void initUi() {
        Glide.with(getBaseActivity())
                .load(user.getUserData().getAvatar())
                .into(ivAvatar);

    }

    @OnClick(R.id.btn_view_teams)
    public void viewTeamsBtnClicked() {
        Intent intent = new Intent(getBaseActivity(), TeamActivity.class);
        intent.putExtra(EXTRA_TEAMS, user.getUserData().getTeams());
        startActivity(intent);
    }

}

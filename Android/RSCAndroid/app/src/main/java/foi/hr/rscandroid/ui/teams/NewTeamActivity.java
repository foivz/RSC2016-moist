package foi.hr.rscandroid.ui.teams;

import com.rengwuxian.materialedittext.MaterialEditText;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class NewTeamActivity extends BaseActivity implements NewTeamView {

    @BindView(R.id.et_team_name)
    MaterialEditText etTeamName;

    @BindView(R.id.et_max_team_members)
    MaterialEditText etMaxTeamMembers;

    @BindView(R.id.btn_next)
    Button btnNext;

    private NewTeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_team);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);
    }

    @OnClick(R.id.btn_next)
    protected void confirm() {
        presenter.createTeam(etTeamName.getText().toString(), etMaxTeamMembers.getText().toString());
    }

    @Override
    public void showNewTeam(Team team) {
        Intent intent = new Intent();
        intent.putExtra(TeamActivity.EXTRA_TEAM, team);
        setResult(RESULT_OK, intent);
        finish();
    }
}

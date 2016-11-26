package foi.hr.rscandroid.ui.teams;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.profile.ProfileFragment;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class TeamActivity extends BaseActivity implements TeamView {

    @BindView(R.id.rv_teams)
    RecyclerView rvTeams;

    private ArrayList<Team> teams = new ArrayList<>();

    private TeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);
        if (getIntent().getSerializableExtra(ProfileFragment.EXTRA_TEAMS) != null) {
            teams = (ArrayList<Team>) getIntent().getSerializableExtra(ProfileFragment.EXTRA_TEAMS);
        }

        presenter.checkTeams(teams);
    }

    @Override
    public void populateAdapter(ArrayList<Team> teams) {
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        rvTeams.setAdapter(new TeamsAdapter(teams, this));
    }

    @Override
    public void showNoTeamsView() {

    }
}

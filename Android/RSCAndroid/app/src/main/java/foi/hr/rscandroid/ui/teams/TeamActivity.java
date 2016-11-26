package foi.hr.rscandroid.ui.teams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.profile.ProfileFragment;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;

public class TeamActivity extends BaseActivity implements TeamView {

    @BindView(R.id.rv_teams)
    RecyclerView rvTeams;

    @BindView(R.id.no_teams_container)
    LinearLayout noTeamsContainer;


    @BindView(R.id.fab)
    FloatingActionButton fab;

    private ArrayList<Team> teams = new ArrayList<>();

    private TeamPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);
        if (getIntent().getSerializableExtra(ProfileFragment.EXTRA_TEAMS) != null) {
            teams = getIntent().getParcelableExtra(ProfileFragment.EXTRA_TEAMS);
        }

        presenter.checkTeams(teams);
    }

    @Override
    public void populateAdapter(ArrayList<Team> teams) {
        noTeamsContainer.setVisibility(View.GONE);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        rvTeams.setAdapter(new TeamsAdapter(teams, this));
    }

    @Override
    public void showNoTeamsView() {
        noTeamsContainer.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.fab)
    public void fabClicked() {
        startActivity(new Intent(this, NewTeamActivity.class));
    }
}

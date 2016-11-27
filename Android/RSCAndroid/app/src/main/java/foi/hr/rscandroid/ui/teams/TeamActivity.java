package foi.hr.rscandroid.ui.teams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
import foi.hr.rscandroid.ui.qr.BarCodeActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.shared.OnTeamClickListener;
import foi.hr.rscandroid.ui.tdetails.TeamDetailsActivity;

public class TeamActivity extends BaseActivity implements TeamView, OnTeamClickListener {

    public static final String EXTRA_TEAM_ID = "EXTRA_TEAM_ID";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

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

        initUi();
        presenter.checkTeams(teams);
    }

    private void initUi() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_teams, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.scan:

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void populateAdapter(ArrayList<Team> teams) {
        noTeamsContainer.setVisibility(View.GONE);
        rvTeams.setVisibility(View.VISIBLE);
        rvTeams.setLayoutManager(new LinearLayoutManager(this));
        rvTeams.setAdapter(new TeamsAdapter(teams, this, rvTeams, this));
    }

    @Override
    public void showNoTeamsView() {
        rvTeams.setVisibility(View.GONE);
        noTeamsContainer.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.fab)
    public void fabClicked() {
        startActivity(new Intent(this, NewTeamActivity.class));
    }

    @Override
    public void onTeamClicked(Team team) {
        Intent intent = new Intent(this, TeamDetailsActivity.class);
        intent.putExtra(EXTRA_TEAM_ID, team.getTeamId());
        startActivity(intent);
    }

    @Override
    public void onRecruit(Team team) {
        startActivity(BarCodeActivity.newInstance(this, "team_id=" + team.getTeamId()));
    }
}

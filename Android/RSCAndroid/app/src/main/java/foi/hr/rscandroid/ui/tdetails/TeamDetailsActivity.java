package foi.hr.rscandroid.ui.tdetails;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.TeamDetailsData;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.teams.TeamActivity;

public class TeamDetailsActivity extends BaseActivity implements TeamDetailsView {

    @BindView(R.id.team_list)
    RecyclerView teamList;

    private TeamDetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_details);
        ButterKnife.bind(this);
        presenter = MvpFactoryUtil.getPresenter(this);

        long id = getIntent().getLongExtra(TeamActivity.EXTRA_TEAM_ID, -1);
        if (id != -1) {
            presenter.fetchTeamDetails(id);
        } else {
            showMessage("Teams are currently unavailable");
        }
    }

    @Override
    public void showTeamDetails(TeamDetailsData detailsData) {
        teamList.setLayoutManager(new LinearLayoutManager(this));
        teamList.setAdapter(new TeamMembersAdapter(detailsData.getUserArrayList(), this));

    }
}

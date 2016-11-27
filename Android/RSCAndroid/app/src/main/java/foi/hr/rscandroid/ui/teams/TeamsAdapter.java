package foi.hr.rscandroid.ui.teams;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.shared.OnTeamClickListener;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Team> teams = new ArrayList<>();

    private Context context;

    private RecyclerView rvTeams;

    private OnTeamClickListener listener;

    public TeamsAdapter(ArrayList<Team> teams, Context context, RecyclerView rvTeams, OnTeamClickListener listener) {
        this.teams = teams;
        this.context = context;
        this.rvTeams = rvTeams;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_team, parent, false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvTeamName.setText(teams.get(position).getTeamName());
        holder.tvMaxMembers.setText(context.getString(R.string.max_slots_in_team, teams.get(position).getMaxAmountOfMembers()));
        holder.recruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecruit(teams.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    @Override
    public void onClick(View view) {
        int itemPos = rvTeams.getChildLayoutPosition(view);
        listener.onTeamClicked(teams.get(itemPos));
    }

    public void add(Team team) {
        teams.add(team);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_team_name)
        TextView tvTeamName;

        @BindView(R.id.tv_max_members)
        TextView tvMaxMembers;

        @BindView(R.id.recruitButton)
        Button recruit;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

package foi.hr.rscandroid.ui.teams;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.ViewHolder> {

    private ArrayList<Team> teams = new ArrayList<>();

    private Context context;

    public TeamsAdapter(ArrayList<Team> teams, Context context) {
        this.teams = teams;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTeamName.setText(teams.get(position).getTeamName());
        holder.tvCurrMembers.setText(context.getString(R.string.team_curr_members_string));
        holder.tvMaxMembers.setText(context.getString(R.string.max_slots_in_team));
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_team_name)
        TextView tvTeamName;

        @BindView(R.id.tv_curr_members)
        TextView tvCurrMembers;

        @BindView(R.id.tv_max_members)
        TextView tvMaxMembers;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

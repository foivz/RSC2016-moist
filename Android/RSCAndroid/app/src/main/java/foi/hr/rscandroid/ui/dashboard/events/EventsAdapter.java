package foi.hr.rscandroid.ui.dashboard.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy. ";

    public static final String METERS = " m";

    public static final String KILOMETERS = " km";

    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    private Context context;

    private long currentUserId;

    private List<Event> events;

    public EventsAdapter(Context context, long currentUserId, List<Event> events) {
        this.context = context;
        this.currentUserId = currentUserId;
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event event = events.get(position);
        holder.title.setText(event.getName());

        if (event.getDate() != null) {
            String date = new SimpleDateFormat(DATE_TIME_PATTERN).format(event.getDate().toDate());
            if (!TextUtils.isEmpty(event.getTime())) {
                date += event.getTime();
            }

            holder.date.setText(date);
        }

        float distance = event.getDistanceFromCurrentLocation();
        if (distance != Event.INVALID_DISTANCE) {
            holder.distance.setVisibility(View.VISIBLE);

            String unit = METERS;
            if (distance > 1000) {
                unit = KILOMETERS;
                distance /= 1000;
            }

            holder.distance.setText(decimalFormat.format(distance) + unit);
        } else {
            holder.distance.setVisibility(View.GONE);
        }

        holder.moderatorFlag.setVisibility(currentUserId == event.getModeratorId() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.date)
        TextView date;

        @BindView(R.id.distance)
        TextView distance;

        @BindView(R.id.moderator_flag)
        TextView moderatorFlag;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

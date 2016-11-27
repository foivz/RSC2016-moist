package foi.hr.rscandroid.ui.dashboard.events;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> {

    public interface EventClickListener {

        void onEventSelected(Event event);
    }

    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy.";

    public static final String METERS = " m";

    public static final String KILOMETERS = " km";

    private DecimalFormat decimalFormat = new DecimalFormat("#.#");

    private Context context;

    @DrawableRes
    private int iconRes;

    @ColorRes
    private int decoratorColor;

    private List<Event> events;

    private EventClickListener listener;

    public EventsAdapter(Context context, @DrawableRes int iconRes, @ColorRes int decoratorColor, List<Event> events,
                         EventClickListener listener) {
        this.context = context;
        this.iconRes = iconRes;
        this.decoratorColor = decoratorColor;
        this.events = events;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_event, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Event event = events.get(position);

        holder.icon.setImageResource(iconRes);
        holder.title.setText(event.getName());
        String date = event.getDate();
        if (event.getDate() != null) {
            if (!TextUtils.isEmpty(event.getTime())) {
                date += " ";
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

        holder.moderatorFlag.setTextColor(ResourcesCompat.getColor(context.getResources(), decoratorColor, null));
        holder.moderatorFlag.setVisibility(event.isUserModerator() ? View.VISIBLE : View.GONE);

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEventSelected(events.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.contentView)
        ViewGroup content;

        @BindView(R.id.icon)
        ImageView icon;

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

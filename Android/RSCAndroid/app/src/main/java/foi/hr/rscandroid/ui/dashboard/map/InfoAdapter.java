package foi.hr.rscandroid.ui.dashboard.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;

public class InfoAdapter implements GoogleMap.InfoWindowAdapter {

    private Context context;

    private List<Event> events;

    public InfoAdapter(Context context, List<Event> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        try {
            ViewHolder viewHolder;

            String id = marker.getSnippet();
            Event event = findEvent(id);

            View view = LayoutInflater.from(context).inflate(R.layout.view_marker, null);
            viewHolder = new ViewHolder(view);

            viewHolder.title.setText(event.getName());
            // TODO: add

            return view;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Event findEvent(String idString) {
        long id = 0;
        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    static class ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.categories)
        TextView categories;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}

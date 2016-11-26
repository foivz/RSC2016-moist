package foi.hr.rscandroid.ui.dashboard.events;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.ui.BaseFragment;

public class EventsFragment extends BaseFragment {

    public static final String EXTRA_COLOR = "color";

    public static final String EXTRA_EVENTS = "events";

    @BindView(R.id.eventList)
    RecyclerView eventList;

    public static EventsFragment newInstance() {
        Bundle args = new Bundle();
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static EventsFragment newInstance(@ColorInt int color, ArrayList<Event> events) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_COLOR, color);
        args.putParcelableArrayList(EXTRA_EVENTS, events);
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        ButterKnife.bind(this, view);
        initUi();
        return view;
    }

    private void initUi() {
        eventList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Event> events = getArguments().getParcelableArrayList(EXTRA_EVENTS);
        if (events != null && events.size() > 0) {
            eventList.setAdapter(new EventsAdapter(getContext(), events));
        } else {
            // show empty placeholder
        }
    }
}

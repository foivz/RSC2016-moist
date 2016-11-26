package foi.hr.rscandroid.ui.dashboard.events;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.ui.BaseFragment;

public class EventsFragment extends BaseFragment {

    public static final String EXTRA_COLOR = "color";

    public static final String EXTRA_EVENTS = "events";

    @BindView(R.id.dummyText)
    TextView dummyText;

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
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        ButterKnife.bind(this, view);
        dummyText.setTextColor(getArguments().getInt("color"));
        return view;
    }
}

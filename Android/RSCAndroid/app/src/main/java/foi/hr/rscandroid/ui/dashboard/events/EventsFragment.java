package foi.hr.rscandroid.ui.dashboard.events;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import foi.hr.rscandroid.ui.details.EventDetailsActivity;

public class EventsFragment extends BaseFragment {

    public static final String EXTRA_COLOR = "color";

    public static final String EXTRA_EVENTS = "events";

    public static final String EXTRA_EMPTY = "empty";

    public static final String EXTRA_DECORATOR = "decorator";

    public static final String EXTRA_ICON = "icon";

    public static final String EXTRA_PRIMARY = "primary";

    @BindView(R.id.eventList)
    RecyclerView eventList;

    @BindView(R.id.emptyView)
    TextView emptyView;

    @ColorRes
    private int primaryColor;

    public static EventsFragment newInstance() {
        Bundle args = new Bundle();
        EventsFragment fragment = new EventsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static EventsFragment newInstance(@ColorInt int color, @DrawableRes int iconRes, @ColorRes int primaryColor,
                                             @ColorRes int decoratorColor, @StringRes int emptyTextId,
                                             ArrayList<Event> events) {
        Bundle args = new Bundle();
        args.putInt(EXTRA_COLOR, color);
        args.putInt(EXTRA_ICON, iconRes);
        args.putInt(EXTRA_PRIMARY, primaryColor);
        args.putInt(EXTRA_DECORATOR, decoratorColor);
        args.putInt(EXTRA_EMPTY, emptyTextId);
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
        int iconRes = getArguments().getInt(EXTRA_ICON);
        int decoratorColor = getArguments().getInt(EXTRA_DECORATOR);
        primaryColor = getArguments().getInt(EXTRA_PRIMARY);

        emptyView.setText(getArguments().getInt(EXTRA_EMPTY));
        emptyView.setTextColor(ResourcesCompat.getColor(getContext().getResources(), decoratorColor, null));

        eventList.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Event> events = getArguments().getParcelableArrayList(EXTRA_EVENTS);
        if (events != null && events.size() > 0) {
            eventList.setAdapter(new EventsAdapter(getContext(), iconRes, decoratorColor, events, listener));
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    private EventsAdapter.EventClickListener listener = new EventsAdapter.EventClickListener() {
        @Override
        public void onEventSelected(Event event) {
            startActivity(EventDetailsActivity.newInstance(getContext(), event, primaryColor));
        }
    };
}

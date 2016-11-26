package foi.hr.rscandroid.ui.dashboard.upcoming;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseFragment;

public class UpcomingFragment extends BaseFragment {

    @BindView(R.id.dummyText)
    TextView dummyText;

    public static UpcomingFragment newInstance() {
        Bundle args = new Bundle();
        UpcomingFragment fragment = new UpcomingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static UpcomingFragment newInstance(int color) {
        Bundle args = new Bundle();
        args.putInt("color", color);
        UpcomingFragment fragment = new UpcomingFragment();
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

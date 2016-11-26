package foi.hr.rscandroid.ui.dashboard.map;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseFragment;

public class MapFragment extends BaseFragment {

    @BindView(R.id.container)
    RelativeLayout myContainer;

    public static MapFragment newInstance() {
        Bundle args = new Bundle();
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MapFragment newInstance(int color) {
        Bundle args = new Bundle();
        args.putInt("color", color);
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dummy, container, false);
        ButterKnife.bind(this, view);
        myContainer.setBackgroundColor(getArguments().getInt("color"));
        return view;
    }
}

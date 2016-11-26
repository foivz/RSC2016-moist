package foi.hr.rscandroid.ui.dashboard;

import com.roughike.bottombar.BottomBar;

import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseActivity;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
    }
}

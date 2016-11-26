package foi.hr.rscandroid.ui.dashboard;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.events.EventsFragment;
import foi.hr.rscandroid.ui.dashboard.map.MapFragment;
import foi.hr.rscandroid.ui.dashboard.profile.ProfileFragment;
import foi.hr.rscandroid.ui.dashboard.upcoming.UpcomingFragment;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    private UpcomingFragment upcomingFragment;

    private EventsFragment eventsFragment;

    private MapFragment mapFragment;

    private ProfileFragment profileFragment;

    private OnTabSelectListener tabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            switch (tabId) {
                case R.id.tab_upcoming:
                    switchFragment(UpcomingFragment.newInstance(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null)));
                    break;

                case R.id.tab_events:
                    switchFragment(EventsFragment.newInstance(ResourcesCompat.getColor(getResources(), R.color.tab_events, null)));
                    break;

                case R.id.tab_map:
                    switchFragment(MapFragment.newInstance(ResourcesCompat.getColor(getResources(), R.color.tab_map, null)));
                    break;

                case R.id.tab_profile:
                    switchFragment(ProfileFragment.newInstance(ResourcesCompat.getColor(getResources(), R.color.tab_profile, null)));
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        initFragments();
        initUi();
    }

    private void initFragments() {
//        upcomingFragment = UpcomingFragment.newInstance();
//        eventsFragment = EventsFragment.newInstance();
//        mapFragment = MapFragment.newInstance();
//        profileFragment = ProfileFragment.newInstance();
    }

    private void initUi() {
        bottomBar.setOnTabSelectListener(tabSelectListener);

        // Initial fragment
        switchFragment(UpcomingFragment.newInstance(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null)));
    }

    private void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fragmentContainer, fragment)
                .commit();
        getSupportFragmentManager().executePendingTransactions();
    }
}

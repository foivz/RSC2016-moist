package foi.hr.rscandroid.ui.dashboard;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
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

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    private int currentColor;

    private UpcomingFragment upcomingFragment;

    private EventsFragment eventsFragment;

    private MapFragment mapFragment;

    private ProfileFragment profileFragment;

    private OnTabSelectListener tabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            int previousColor = currentColor;
            switch (tabId) {
                case R.id.tab_upcoming:
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null));
                    switchFragment(UpcomingFragment.newInstance(currentColor));
                    break;

                case R.id.tab_events:
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_events, null));
                    switchFragment(EventsFragment.newInstance(currentColor));
                    break;

                case R.id.tab_map:
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_map, null));
                    switchFragment(MapFragment.newInstance(currentColor));
                    break;

                case R.id.tab_profile:
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_profile, null));
                    switchFragment(ProfileFragment.newInstance(currentColor));
                    break;

                default:
                    break;
            }
            animateToolbarColorChange(previousColor, currentColor);
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
        toolbar.setTitle("Hello, Jozo");

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

    private void setCurrentColor(int color) {
        currentColor = color;
    }

    private void setToolbarColor(int color) {
        toolbar.setBackgroundColor(color);
    }

    private void animateToolbarColorChange(int oldColor, int newColor) {
        ValueAnimator animator = new ValueAnimator();
        animator.setIntValues(oldColor, newColor);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setDuration(150);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                setToolbarColor((int) valueAnimator.getAnimatedValue());
            }
        });
        animator.start();
    }
}

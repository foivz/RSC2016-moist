package foi.hr.rscandroid.ui.dashboard;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.events.EventsFragment;
import foi.hr.rscandroid.ui.dashboard.map.MapFragment;
import foi.hr.rscandroid.ui.dashboard.profile.ProfileFragment;
import foi.hr.rscandroid.ui.dashboard.upcoming.UpcomingFragment;
import foi.hr.rscandroid.ui.login.LoginActivity;
import foi.hr.rscandroid.ui.shared.ColorUtils;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @BindInt(R.integer.color_change_duration)
    int colorChangeDuration;

    private int currentColor;

    private UpcomingFragment upcomingFragment;

    private EventsFragment eventsFragment;

    private MapFragment mapFragment;

    private ProfileFragment profileFragment;

    private UserRequest user;

    private ImageButton menuButton;

    private int tabNumber = 0;

    private OnTabSelectListener tabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            int previousColor = currentColor;
            invalidateOptionsMenu();
            switch (tabId) {
                case R.id.tab_upcoming:
                    tabNumber = 0;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null));
                    switchFragment(UpcomingFragment.newInstance(currentColor));
                    break;

                case R.id.tab_events:
                    tabNumber = 1;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_events, null));
                    switchFragment(EventsFragment.newInstance(currentColor));
                    break;

                case R.id.tab_map:
                    tabNumber = 2;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_map, null));
                    switchFragment(MapFragment.newInstance(currentColor));
                    break;

                case R.id.tab_profile:
                    tabNumber = 3;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_profile, null));
                    switchFragment(ProfileFragment.newInstance(currentColor, user));
                    break;

                default:
                    break;
            }
            animateToolbarColorChange(previousColor, currentColor);
        }
    };

    private ValueAnimator.AnimatorUpdateListener colorChangeListener = new ValueAnimator
            .AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            int color = (int) valueAnimator.getAnimatedValue();
            setToolbarColor(color);
            setStatusBarColor(ColorUtils.getDarkerColor(color));
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        bindDataFromIntent();
        initFragments();
        initUi();
    }

    private void bindDataFromIntent() {
        user = (UserRequest) getIntent().getSerializableExtra(LoginActivity.EXTRA_USER_DATA);
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
        initWindow();

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

    private void initWindow() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    private void setCurrentColor(@ColorInt int color) {
        currentColor = color;
    }

    private void setToolbarColor(@ColorInt int color) {
        toolbar.setBackgroundColor(color);
    }

    private void setStatusBarColor(@ColorInt int color) {
        getWindow().setStatusBarColor(color);
    }

    private void animateToolbarColorChange(@ColorInt int oldColor, @ColorInt int newColor) {
        ValueAnimator animator = ColorUtils.getColorChangeAnimator(oldColor, newColor, colorChangeDuration, colorChangeListener);
        animator.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO Add more icons
        switch (tabNumber) {
            case 3:
                getMenuInflater().inflate(R.menu.menu_profile, menu);
                return true;
            case 0:
            case 1:
            case 2:
            default:
                return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_menu_action:
                Toast.makeText(this, "Menu item clicked", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

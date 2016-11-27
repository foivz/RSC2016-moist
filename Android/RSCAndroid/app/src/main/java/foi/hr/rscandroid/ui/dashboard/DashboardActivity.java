package foi.hr.rscandroid.ui.dashboard;

import com.facebook.login.LoginManager;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.data.models.UserRequest;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.dashboard.events.EventsFragment;
import foi.hr.rscandroid.ui.dashboard.map.CurrentLocationUtil;
import foi.hr.rscandroid.ui.dashboard.map.LocationListener;
import foi.hr.rscandroid.ui.dashboard.map.MapFragment;
import foi.hr.rscandroid.ui.dashboard.profile.ProfileFragment;
import foi.hr.rscandroid.ui.dashboard.upcoming.UpcomingFragment;
import foi.hr.rscandroid.ui.login.LoginActivity;
import foi.hr.rscandroid.ui.login.LoginPresenter;
import foi.hr.rscandroid.ui.shared.ColorUtils;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.shared.SharedPrefsHelper;

public class DashboardActivity extends BaseActivity implements DashboardView {

    public static final int TAB_PROFILE = 3;

    public static final int TAB_MAP = 2;

    public static final int TAB_EVENTS = 1;

    public static final int TAB_UPCOMING = 0;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;

    @BindInt(R.integer.color_change_duration)
    int colorChangeDuration;

    private DashboardPresenter presenter;

    private int currentColor;

    private UpcomingFragment upcomingFragment;

    private EventsFragment eventsFragment;

    private MapFragment mapFragment;

    private ProfileFragment profileFragment;

    private UserRequest user;

    private ImageButton menuButton;

    private int tabNumber = 0;

    private ArrayList<Event> events;

    private OnTabSelectListener tabSelectListener = new OnTabSelectListener() {
        @Override
        public void onTabSelected(@IdRes int tabId) {
            int previousColor = currentColor;
            invalidateOptionsMenu();
            switch (tabId) {
                case R.id.tab_upcoming:
                    tabNumber = TAB_UPCOMING;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null));
                    switchFragment(EventsFragment.newInstance(currentColor, R.drawable.ic_question_teal, R.color.colorAccent,
                            R.string.empty_upcoming, null));
                    setToolbarVisibile(true);
                    break;

                case R.id.tab_events:
                    tabNumber = TAB_EVENTS;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_events, null));
                    switchFragment(EventsFragment.newInstance(currentColor, R.drawable.ic_question_red, R.color.tab_upcoming,
                            R.string.empty_events, events));
                    setToolbarVisibile(true);
                    break;

                case R.id.tab_map:
                    tabNumber = TAB_MAP;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_map, null));
                    switchFragment(MapFragment.newInstance(events));
                    setToolbarVisibile(false);
                    break;

                case R.id.tab_profile:
                    tabNumber = TAB_PROFILE;
                    setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_profile, null));
                    switchFragment(ProfileFragment.newInstance(currentColor, user));
                    setToolbarVisibile(true);
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

        presenter = MvpFactoryUtil.getPresenter(this);
        presenter.init();
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
        setCurrentColor(ResourcesCompat.getColor(getResources(), R.color.tab_upcoming, null));
        switchFragment(EventsFragment.newInstance(currentColor, R.drawable.ic_question_teal, R.color.tab_map,
                R.string.empty_upcoming, null));
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
            case TAB_PROFILE:
                getMenuInflater().inflate(R.menu.menu_profile, menu);
                return true;
            case TAB_UPCOMING:
            case TAB_EVENTS:
            case TAB_MAP:
            default:
                return super.onCreateOptionsMenu(menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.btn_menu_action:
                switch (tabNumber) {
                    case TAB_UPCOMING:
                        break;
                    case TAB_EVENTS:
                        break;
                    case TAB_MAP:
                        break;
                    case TAB_PROFILE:
                        signOut();
                        break;
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setToolbarVisibile(boolean visible) {
        if (visible) {
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }
    }

    private void signOut() {
        SharedPrefsHelper.clearSingleSharedPrefsItem(LoginPresenter.TOKEN);
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onEventsReceived(ArrayList<Event> events) {
        this.events = events;
    }

    @Override
    public void fetchCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, MapFragment.PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, MapFragment.PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED) {
            CurrentLocationUtil.getCurrentLocation(this, new LocationListener() {
                @Override
                public void onLocationRetrieved(Location location) {
                    SharedPrefsHelper.setSharedPrefsString(SharedPrefsHelper.KEY_LAT, String.valueOf(location.getLatitude()));
                    SharedPrefsHelper.setSharedPrefsString(SharedPrefsHelper.KEY_LNG, String.valueOf(location.getLongitude()));
                    presenter.onLocationRetrieved();
                }
            });
        }
    }
}

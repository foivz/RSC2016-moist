package foi.hr.rscandroid.ui.details;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.ColorUtils;

public class EventDetailsActivity extends BaseActivity {

    private static final String DATE_TIME_PATTERN = "dd.MM.yyyy.";

    private static final String EXTRA_EVENT = "event";

    private static final String EXTRA_COLOR = "color";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.mapView)
    MapView mapView;

    @BindView(R.id.startTime)
    TextView startTime;

    @BindView(R.id.description)
    TextView description;

    @BindView(R.id.prizes)
    TextView prizes;

    @BindView(R.id.action)
    Button action;

    @ColorInt
    private int color;

    private Event event;

    public static Intent newInstance(Context context, Event event, @ColorRes int color) {
        Intent intent = new Intent(context, EventDetailsActivity.class);
        intent.putExtra(EXTRA_EVENT, event);
        intent.putExtra(EXTRA_COLOR, color);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        ButterKnife.bind(this);
        mapView.onCreate(savedInstanceState);
        initUi();
    }

    private void initUi() {
        color = ResourcesCompat.getColor(getResources(), getIntent().getIntExtra(EXTRA_COLOR, 0), null);
        event = getIntent().getParcelableExtra(EXTRA_EVENT);

        initBars();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if (googleMap != null) {
                    initMap(googleMap);
                }
            }
        });

        toolbar.setBackgroundColor(color);
        toolbar.setTitle(event.getName());

        String date = new SimpleDateFormat(DATE_TIME_PATTERN).format(event.getDate().toDate());
        if (!TextUtils.isEmpty(event.getTime())) {
            date += " ";
            date += event.getTime();
        }
        startTime.setText(date);

        description.setText(event.getDescription());
        prizes.setText(event.getPrizes());

        if (!event.isUserModerator()) {
            action.setText("Join quiz!");
        }
    }

    private void initBars() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        initWindow();
        getWindow().setStatusBarColor(ColorUtils.getDarkerColor(color));
    }

    private void initWindow() {
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }

    private void initMap(@NonNull GoogleMap googleMap) {
        LatLng latLng = new LatLng(event.getLatitude(), event.getLongitude());
        MapsInitializer.initialize(this);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_small)));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    @OnClick(R.id.action)
    protected void doAction() {
        showMessage("REJECTED!");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }
}

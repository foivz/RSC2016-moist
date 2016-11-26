package foi.hr.rscandroid.ui.dashboard.map;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Event;
import foi.hr.rscandroid.ui.PermissionFragment;

public class MapFragment extends PermissionFragment implements OnMapReadyCallback {

    private static final String[] PERMISSIONS = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    private static final double DEFAULT_LAT = 46.3076267;

    private static final double DEFAULT_LON = 16.3363742;

    private static final float ANIMATION_SPEED = 16.0f;

    public static final String EXTRA_EVENTS = "events";

    @BindView(R.id.mapView)
    MapView mapView;

    private GoogleMap googleMap;

    private ArrayList<Event> events;

    private boolean eventsShown;

    public static MapFragment newInstance(ArrayList<Event> events) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(EXTRA_EVENTS, events);
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, view);

        events = getArguments().getParcelableArrayList(EXTRA_EVENTS);

        mapView.onCreate(savedInstanceState);
        initUi();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requestPermissionsWithRationale();
    }

    private void requestPermissionsWithRationale() {
        if (hasPermissions(PERMISSIONS)) {
            onPermissionsGranted();
        } else if (showPermissionRationale(PERMISSIONS)) {
            showMessage(getString(R.string.location_rationale), new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    requestPermissions(PERMISSIONS);
                }
            });
        } else {
            requestPermissions(PERMISSIONS);
        }
    }

    @Override
    protected void onPermissionsGranted() {
        if (googleMap != null) {
            initUsersLocation();
            showEventLocations();
        }
    }

    @Override
    protected void onPermissionsDenied(String[] permissions, int[] grantResults) {
        showDefaultLocationMarker();
        showEventLocations();
    }

    private void initUi() {
        mapView.getMapAsync(this);
    }

    @SuppressWarnings("MissingPermission")
    private void initUsersLocation() {
        if (googleMap != null) {
            MapsInitializer.initialize(getContext());
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            fetchCurrentLocation();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // TODO: navigate to details
                if (marker.getSnippet() != null) {
                    long id = Long.parseLong(marker.getSnippet());
                }
            }
        });
        if (hasPermissions(PERMISSIONS) && !eventsShown) {
            initUsersLocation();
            showEventLocations();
        }
    }

    private void showEventLocations() {
        googleMap.clear();
        eventsShown = true;
        for (Event event : events) {
            showEventLocation(event);
        }
        googleMap.setInfoWindowAdapter(new InfoAdapter(getContext(), events));
    }

    private void showDefaultLocationMarker() {
        LatLng latLng = new LatLng(DEFAULT_LAT, DEFAULT_LON);
        googleMap.addMarker(new MarkerOptions().position(latLng).title(getString(R.string.your_location)));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ANIMATION_SPEED));
    }

    private void showCurrentLocation(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, ANIMATION_SPEED));
    }

    private void fetchCurrentLocation() {
        CurrentLocationUtil.getCurrentLocation(getContext(), new LocationListener() {
            @Override
            public void onLocationRetrieved(Location location) {
                if (location != null) {
                    showCurrentLocation(location);
                }
            }
        });
    }

    private void showEventLocation(Event event) {
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_small);
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(event.getLatitude(), event.getLongitude()))
                .title(event.getName())
                .icon(bitmap)
                .snippet(String.valueOf(event.getId())));
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

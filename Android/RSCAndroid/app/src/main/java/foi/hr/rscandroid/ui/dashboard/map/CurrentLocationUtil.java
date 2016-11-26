package foi.hr.rscandroid.ui.dashboard.map;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class CurrentLocationUtil implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private static CurrentLocationUtil instance;

    private GoogleApiClient googleApiClient;

    private LocationListener listener;

    private boolean initialized;

    private static CurrentLocationUtil getInstance() {
        if (instance == null) {
            instance = new CurrentLocationUtil();
        }
        return instance;
    }

    private void setListener(LocationListener listener) {
        this.listener = listener;
    }

    public static void getCurrentLocation(Context context, LocationListener listener) {
        CurrentLocationUtil instance = getInstance();
        instance.setListener(listener);
        instance.init(context);
        instance.connect();
    }

    private CurrentLocationUtil() {
    }

    private void init(Context context) {
        googleApiClient = new GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        initialized = true;
    }

    private void connect() {
        if (initialized) {
            googleApiClient.connect();
        }
    }

    private void disconnect() {
        if (initialized) {
            googleApiClient.disconnect();
        }
    }

    @SuppressWarnings("MissingPermission")
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (lastKnownLocation == null) {
            disconnect();
            connect();
        } else {
            listener.onLocationRetrieved(lastKnownLocation);
            disconnect();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

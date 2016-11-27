package foi.hr.rscandroid.ui.qr;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import foi.hr.rscandroid.R;
import foi.hr.rscandroid.data.models.Team;
import foi.hr.rscandroid.ui.BaseActivity;
import foi.hr.rscandroid.ui.shared.MvpFactoryUtil;
import foi.hr.rscandroid.ui.teams.TeamActivity;
import pub.devrel.easypermissions.EasyPermissions;

public class ScannerActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, ScannerView {

    @BindView(R.id.surfacePreview)
    SurfacePreview surfacePreview;

    private CameraSource cameraSource;

    private Handler handler = new Handler(Looper.getMainLooper());

    private ScannerPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);
        ButterKnife.bind(this);

        presenter = MvpFactoryUtil.getPresenter(this);

        if (EasyPermissions.hasPermissions(this, Manifest.permission.CAMERA)) {
            createCamera();
            startCamera();
        } else {
            EasyPermissions.requestPermissions(this, "Bar code scanning requires your permission to use the camera", 430, Manifest
                    .permission.CAMERA);
        }
    }

    private void createCamera() {
        final BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory();
        barcodeDetector.setProcessor(new MultiProcessor.Builder<>(barcodeFactory).build());
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setAutoFocusEnabled(true)
                .build();
    }

    private void startCamera() {
        try {
            surfacePreview.start(cameraSource);
        } catch (IOException e) {
            surfacePreview.release();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        surfacePreview.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        surfacePreview.release();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        createCamera();
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public void showNewTeam(Team team) {
        Intent intent = new Intent();
        intent.putExtra(TeamActivity.EXTRA_TEAM, team);
        setResult(RESULT_OK, intent);
        finish();
    }

    class BarcodeTrackerFactory implements MultiProcessor.Factory<Barcode> {

        @Override
        public Tracker<Barcode> create(Barcode barcode) {
            return new LotteryBarcodeTracker();
        }
    }

    class LotteryBarcodeTracker extends Tracker<Barcode> {

        static final long RATE_LIMIT_MS = 200;

        private long lastScannedTimestamp = 0;

        @Override
        public synchronized void onUpdate(Detector.Detections<Barcode> detectionResults, final Barcode barcode) {
            // simple rate limiting, because it's just too fast!
            if (System.currentTimeMillis() > lastScannedTimestamp + RATE_LIMIT_MS) {
                lastScannedTimestamp = System.currentTimeMillis();
            } else {
                return;
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    presenter.proccessBarcode(barcode.displayValue);
                }
            });
        }
    }
}

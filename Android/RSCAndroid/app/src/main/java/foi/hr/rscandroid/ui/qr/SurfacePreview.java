package foi.hr.rscandroid.ui.qr;

import com.google.android.gms.vision.CameraSource;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class SurfacePreview extends SurfaceView {

    private CameraSource cameraSource;

    private boolean shouldStart;

    private boolean available;

    public SurfacePreview(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(new SurfaceCallback());
    }

    public void start(CameraSource cameraSource) throws IOException {
        this.cameraSource = cameraSource;

        if (cameraSource == null) {
            stop();
        }

        if (this.cameraSource != null) {
            shouldStart = true;
            start();
        }
    }

    public void stop() {
        if (cameraSource != null) {
            cameraSource.stop();
        }
    }

    public void release() {
        if (cameraSource != null) {
            cameraSource.release();
            cameraSource = null;
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("MissingPermission")
    private void start() throws IOException {
        if (!shouldStart || !available) {
            return;
        }
        cameraSource.start(getHolder());
        shouldStart = false;

    }

    private class SurfaceCallback implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder surface) {
            available = true;
            try {
                start();
            } catch (Exception e) {

            }

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surface) {
            available = false;
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }
    }
}

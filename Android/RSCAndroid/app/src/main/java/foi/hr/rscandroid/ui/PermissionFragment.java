package foi.hr.rscandroid.ui;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public abstract class PermissionFragment extends BaseFragment {

    protected static final int REQUEST_CODE_PERMISSION = 420;

    protected abstract void onPermissionsGranted();

    protected abstract void onPermissionsDenied(String[] permissions, int[] grantResults);

    protected boolean hasPermissions(String... permissions) {
        if (getActivity() != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    protected void requestPermissions(String... permissions) {
        requestPermissions(permissions, REQUEST_CODE_PERMISSION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            defaultPermissionsResult(permissions, grantResults);
        } else {
            requestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void defaultPermissionsResult(String[] permissions, int[] grantResults) {
        if (arePermissionsGranted(grantResults)) {
            onPermissionsGranted();
        } else {
            onPermissionsDenied(permissions, grantResults);
        }
    }

    protected void requestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        defaultPermissionsResult(permissions, grantResults);
    }

    protected final boolean arePermissionsGranted(int[] results) {
        for (int result : results) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    protected boolean showPermissionRationale(String[] permissionLocation) {
        for (String permission : permissionLocation) {
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }
}

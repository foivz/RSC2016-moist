package foi.hr.rscandroid.ui;

import com.afollestad.materialdialogs.MaterialDialog;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import foi.hr.rscandroid.R;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseMvp.View {

    private MaterialDialog progressDialog;

    @Override
    public void showProgress() {
        if (progressDialog == null) {
            progressDialog = new MaterialDialog.Builder(this)
                    .cancelable(false)
                    .title(R.string.app_name)
                    .content(R.string.loading)
                    .progress(true, 0)
                    .build();
            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        showMessage(getString(messageResId));
    }

    @Override
    public void showMessage(String message) {
        showMessage(message, R.string.ok, null, 0, null);
    }

    @Override
    public void showMessage(String message, MaterialDialog.SingleButtonCallback okListener) {
        showMessage(message, R.string.ok, okListener, 0, null);
    }

    @Override
    public void showMessage(String message, @StringRes int positiveText, MaterialDialog.SingleButtonCallback positiveListener, @StringRes int negativeText,
                            MaterialDialog.SingleButtonCallback negativeListener) {
        MaterialDialog.Builder builder = new MaterialDialog.Builder(this);
        builder.title(R.string.app_name)
                .content(message)
                .cancelable(false)
                .positiveText(positiveText)
                .onPositive(positiveListener)
                .negativeText(negativeText)
                .onNegative(negativeListener)
                .show();
    }
}

package foi.hr.rscandroid;

import com.afollestad.materialdialogs.MaterialDialog;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public abstract class BaseFragment extends Fragment implements BaseMvp.View {

    protected BaseActivity getBaseActivity() {
        return getActivity() instanceof BaseActivity ? ((BaseActivity) getActivity()) : null;
    }

    @Override
    public void showProgress() {
        if (isValid()) {
            getBaseActivity().showProgress();
        }
    }

    @Override
    public void hideProgress() {
        if (isValid()) {
            getBaseActivity().hideProgress();
        }
    }

    @Override
    public void showMessage(@StringRes int messageResId) {
        if (isValid()) {
            getBaseActivity().showMessage(messageResId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (isValid()) {
            getBaseActivity().showMessage(message);
        }
    }

    @Override
    public void showMessage(String message, MaterialDialog.SingleButtonCallback okListener) {
        if (isValid()) {
            getBaseActivity().showMessage(message, okListener);
        }
    }

    @Override
    public void showMessage(String message, @StringRes int positiveText, MaterialDialog.SingleButtonCallback positiveListener,
                            @StringRes int negativeText, MaterialDialog.SingleButtonCallback negativeListener) {
        if (isValid()) {
            getBaseActivity().showMessage(message, positiveText, positiveListener, negativeText, negativeListener);
        }
    }

    protected boolean isValid() {
        return getBaseActivity() != null && !getBaseActivity().isFinishing() && !isDetached() && isAdded();
    }
}

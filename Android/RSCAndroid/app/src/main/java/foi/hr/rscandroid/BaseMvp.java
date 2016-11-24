package foi.hr.rscandroid;

import com.afollestad.materialdialogs.MaterialDialog;

import android.support.annotation.StringRes;

/**
 * @author Igor Tepavac
 *         igor.tepavac@infinum.hr
 * @since 24/11/16
 */

public interface BaseMvp {

    interface View {

        void showProgress();

        void hideProgress();

        void showMessage(@StringRes int messageResId);

        void showMessage(String message);

        void showMessage(String message, MaterialDialog.SingleButtonCallback okListener);

        void showMessage(String message, @StringRes int positiveText, MaterialDialog.SingleButtonCallback positiveListener,
                         @StringRes int negativeText, MaterialDialog.SingleButtonCallback negativeListener);
    }

    // TODO: add base presenter and interactor only if really needed
}
